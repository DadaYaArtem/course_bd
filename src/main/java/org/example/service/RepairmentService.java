package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Repairment;
import org.example.repository.RepairmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairmentService {
    private final RepairmentRepository repairmentRepository;

    public List<Repairment> getAllRepairments() {
        return repairmentRepository.findAll();
    }

    public List<Repairment> getAllRepairmentsByEquip(Long id) {
        return repairmentRepository.findRepairmentsByEquipment(id);
    }

}
