package com.financy.financy.service;

import org.springframework.stereotype.Service;

import com.financy.financy.entity.Parcela;
import com.financy.financy.repository.ParcelaRepository;

@Service
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;

    public ParcelaService(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    public Parcela salvaParcela(Parcela parcela) {
        Parcela parc = this.parcelaRepository.save(parcela);
        return parc;
    }
}
