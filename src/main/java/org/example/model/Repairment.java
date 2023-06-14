package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "repair")
public class Repairment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @Column(name = "repair_date_start", nullable = false)
    private Timestamp repairDateStart;

    @Column(name = "repair_date_finish", nullable = false)
    private Timestamp repairDateFinish;

    @Column(name = "repair_cause", nullable = false)
    private String repairCause;

    @Column(name = "repair_price", nullable = false)
    private Double repairPrice;
}
