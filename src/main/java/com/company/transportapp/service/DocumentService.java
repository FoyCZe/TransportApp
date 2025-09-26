package com.company.transportapp.service;


import com.company.transportapp.model.entities.Document;
import com.company.transportapp.model.enums.Enums;
import com.company.transportapp.model.entities.Transport;
import com.company.transportapp.repository.DocumentRepository;
import com.company.transportapp.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    private final String baseDir = "uploads/";

    public void store(MultipartFile file, Long transportId, Enums.DocumentType type) throws IOException {
        String storedPath = FileStorageUtil.storeFile(file, baseDir + transportId);
        Document doc = Document.builder()
                        .transport(Transport.builder().id(transportId).build())
                        .documentType(type)
                        .filePath(storedPath)
                        .originalFileName(file.getOriginalFilename())
                        .official(false)
                        .uploadedAt(LocalDateTime.now())
                        .build();

        documentRepository.save(doc);
    }

    public Resource loadAsResource(Long id) throws MalformedURLException {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokument nenalezen!"));
        Path path = Paths.get(doc.getFilePath());
        return new UrlResource(path.toUri());
    }

    public void markOfficial(Long transportId) {
        List<Document> docs = documentRepository.findAll().stream()
                .filter(d -> d.getTransport().getId().equals(transportId))
                .collect(Collectors.toList());
        docs.forEach(d -> d.setOfficial(true));
        documentRepository.saveAll(docs);
    }
}
