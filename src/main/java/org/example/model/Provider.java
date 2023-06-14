package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "address", nullable = false)
    private String address;
}
