package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "equip_workplace_movement")
@Data
public class EquipWorkplaceMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    @Column(name = "arriving_date", nullable = false)
    private Timestamp arrivingDate;

    @Column(name = "day_moved")
    private Timestamp dayMoved;
}
