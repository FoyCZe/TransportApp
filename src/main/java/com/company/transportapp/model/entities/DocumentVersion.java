package com.company.transportapp.model.entities;

import com.company.transportapp.model.enums.DocumentEvent;
import com.company.transportapp.model.enums.Enums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_versions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_id")
    private Document document;

    @Column(nullable = false)
    private Integer versionNumber;

    @Column(nullable = false)
    private String filePath;

    private String originalFileName;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentEvent event;  // PICKUP, LOAD, UNLOAD, RETURN

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private Employee uploadedBy;

    private boolean official;  // true = finální verze po ukončení přepravy
}
