package com.company.transportapp.repository;


import com.company.transportapp.model.entities.Document;
import com.company.transportapp.model.enums.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByTransport_IdOrderByUploadedAtAsc(Long transportId);
    List<Document> findByTransport_IdAndDocumentTypeOrderByVersionAsc(Long transportId, Enums.DocumentType type);
}
