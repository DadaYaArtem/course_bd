package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Revaluation;
import org.example.repository.RevaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevaluationService {
    private final RevaluationRepository revaluationRepository;

    public List<Revaluation> getAllRevals() {
        return revaluationRepository.findAll();
    }

    public List<Revaluation> getAllRevalsByEquip(Long id) {
        return revaluationRepository.findRevalByEquip(id);
    }
}
