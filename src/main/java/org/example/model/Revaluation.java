package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Revaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @Column(name = "reval_cause", nullable = false, length = 255)
    private String revaluationCause;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "new_price", nullable = false)
    private Double newPrice;

    @Column(name = "date_changed", nullable = false)
    private Timestamp dateChanged;
}
