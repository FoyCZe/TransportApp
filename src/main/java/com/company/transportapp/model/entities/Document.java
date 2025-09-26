package com.company.transportapp.model.entities;


import com.company.transportapp.model.enums.Enums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(nullable=false)
    private String filePath;  // Abolutní/relativní cesta na FS

    private String originalFileName; // Název souboru
    private Integer version;  // Verze souboru
    private boolean official;  // Označení "oficiální"
    private LocalDateTime uploadedAt;

}
