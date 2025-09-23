package com.company.transportapp.service;


import com.company.transportapp.model.Document;
import com.company.transportapp.model.Transport;
import com.company.transportapp.repository.DocumentRepository;
import com.company.transportapp.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
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

    public void store(MultipartFile file, Long transportId, String type) throws IOException {
        String filename = FileStorageUtil.storeFile(file, baseDir + transportId);
        Document doc = new Document();
        doc.setFilename(filename);
        doc.setType(type);
        doc.setOfficial(false);
        doc.setUploadTime(LocalDateTime.now());
        doc.setTransport(new Transport());
        doc.getTransport().setId(transportId);
        documentRepository.save(doc);
    }

    public Resource loadAsResource(Long id) throws MalformedURLException {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dokument nenalezen!"));
        Path path = Paths.get(baseDir + doc.getTransport().getId() + "/" + doc.getFilename());
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
