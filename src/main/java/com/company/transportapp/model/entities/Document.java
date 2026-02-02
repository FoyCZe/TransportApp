package com.company.transportapp.model.entities;


import com.company.transportapp.model.enums.Enums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="documents")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="transport_id")
    private Transport transport;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Enums.DocumentType documentType;  // CMR, T1, INTERCHANGE A DALŠÍ

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("versionNumber ASC")
    @Builder.Default
    private List<DocumentVersion> versions = new ArrayList<>();

    // Aktuální (poslední / platná) verze dokumentu
    private Integer currentVersion;

}
