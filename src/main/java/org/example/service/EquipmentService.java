package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Category;
import org.example.model.Equipment;
import org.example.model.Workplace;
import org.example.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment(){
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> findEquipmentById(Long id){
        return equipmentRepository.findById(id);
    }


    public void deleteEquipById(Long id) {
        equipmentRepository.deleteById(id);
    }

    public void updateEquip(Long id, String name, String model, String description, Equipment.Condition condition, Category category, Workplace workplace) {
        Equipment equipment = findEquipmentById(id).get();
        equipment.setName(name);
        equipment.setModel(model);
        equipment.setDescription(description);
        equipment.setCondition(condition);
        equipment.setCategory(category);
        equipment.setWorkplace(workplace);

        equipmentRepository.save(equipment);
    }
}
