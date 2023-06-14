package org.example.repository;

import org.example.model.Repairment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepairmentRepository extends JpaRepository<Repairment, Long> {
    @Query(value = "select repair.id, equipment_id, repair_date_start, repair_date_finish, repair_cause, repair_price from repair\n" +
            "join equipment on equipment.id = repair.equipment_id\n" +
            "where equipment.id = :equipmentId ",nativeQuery = true)
    List<Repairment> findRepairmentsByEquipment (Long equipmentId);
}
