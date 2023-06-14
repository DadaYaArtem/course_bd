package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Purchase;
import org.example.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchase(){
        return purchaseRepository.findAll();
    }
}
