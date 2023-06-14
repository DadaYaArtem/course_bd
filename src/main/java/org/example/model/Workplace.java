package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workplace")
@Data
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "head_manager", nullable = false)
    private String headManager;
}
