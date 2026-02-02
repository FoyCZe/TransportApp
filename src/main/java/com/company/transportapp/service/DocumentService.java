package com.company.transportapp.service;


import com.company.transportapp.model.entities.Document;
import com.company.transportapp.model.entities.DocumentVersion;
import com.company.transportapp.model.entities.Employee;
import com.company.transportapp.model.entities.Transport;
import com.company.transportapp.model.enums.DocumentEvent;
import com.company.transportapp.model.enums.Enums;
import com.company.transportapp.repository.DocumentRepository;
import com.company.transportapp.repository.DocumentVersionRepository;
import com.company.transportapp.repository.EmployeeRepository;
import com.company.transportapp.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepo;
    private final DocumentVersionRepository versionRepo;
    private final TransportRepository transportRepo;
    private final EmployeeRepository employeeRepo;

    public void uploadNewVersion(Long transportId, Enums.DocumentType type, Long employeeId, String filePath, String originalFileName, DocumentEvent event) {

        Transport transport = transportRepo.findById(transportId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Přeprava nenalezena"));

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zaměstnanec nenalezen"));

        Document document = documentRepo.findByTransportIdAndDocumentType(transportId, type)
                .orElseGet(() -> {
                    Document d = new Document();
                    d.setTransport(transport);
                    d.setDocumentType(type);
                    d.setCurrentVersion(0);
                    return documentRepo.save(d);
                });

        int nextVersion = document.getCurrentVersion() + 1;

        DocumentVersion version = DocumentVersion.builder()
                .document(document)
                .versionNumber(nextVersion)
                .filePath(filePath)
                .originalFileName(originalFileName)
                .event(event)
                .uploadedBy(employee)
                .uploadedAt(LocalDateTime.now())
                .build();

        versionRepo.save(version);

        document.setCurrentVersion(nextVersion);
        document.getVersion().add(version);

        documentRepo.save(document);

    }
}
