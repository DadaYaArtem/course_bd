package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Provider;
import org.example.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }

}
