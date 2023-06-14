package org.example.repository;

import org.example.model.EquipWorkplaceMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipWorkplaceMovementRepository extends JpaRepository<EquipWorkplaceMovement, Long> {
    @Query(value = "SELECT * FROM equip_workplace_movement WHERE equipment_id = :equipmentId", nativeQuery = true)
    List<EquipWorkplaceMovement> findWorkplaceByEquipmentId(Long equipmentId);
}

