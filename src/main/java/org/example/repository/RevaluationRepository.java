package org.example.repository;

import org.example.model.Revaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevaluationRepository extends JpaRepository<Revaluation, Long> {
    @Query(value = "select revaluation.id, equipment_id, reval_cause, base_price, new_price, date_changed from revaluation\n" +
            "join equipment on equipment.id = revaluation.equipment_id\n" +
            "where equipment.id = :equipmentId ",nativeQuery = true)
    List<Revaluation> findRevalByEquip (Long equipmentId);
}
