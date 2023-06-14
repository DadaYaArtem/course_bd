package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.EquipWorkplaceMovement;
import org.example.model.Workplace;
import org.example.repository.EquipWorkplaceMovementRepository;
import org.example.repository.WorkplaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkplaceService {
    private final WorkplaceRepository workplaceRepository;
    private final EquipWorkplaceMovementRepository workplaceMovementRepository;

    public List<Workplace> getAllWorkplaces(){
        return workplaceRepository.findAll();
    }

    public List<EquipWorkplaceMovement> getWorkplacesByEquip (Long id) {
        return workplaceMovementRepository.findWorkplaceByEquipmentId(id);
    }
}
