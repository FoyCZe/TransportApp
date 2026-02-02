package com.company.transportapp.repository;


import com.company.transportapp.model.entities.Document;
import com.company.transportapp.model.enums.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByTransportIdAndDocumentType(Long transportId, Enums.DocumentType type);
}
