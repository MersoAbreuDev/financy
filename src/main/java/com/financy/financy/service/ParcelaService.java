package com.financy.financy.service;


import com.financy.financy.enums.Status;
import com.financy.financy.responseDTO.DividaResponseDTO;
import com.financy.financy.responseDTO.ParcelaResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.financy.financy.entity.Parcela;
import com.financy.financy.repository.ParcelaRepository;


@Service
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;



    private final ModelMapper modelMapper;
    public ParcelaService(ParcelaRepository parcelaRepository,

                          ModelMapper modelMapper) {
        this.parcelaRepository = parcelaRepository;
        this.modelMapper = modelMapper;
    }

    public Parcela salvaParcela(Parcela parcela) {
        Parcela parc = this.parcelaRepository.save(parcela);
        return parc;
    }

    public ParcelaResponseDTO pagarParcela(Long id) {
        Parcela parcela = this.parcelaRepository.findById(id).get();
        parcela.setStatus(Status.PAGO);
        parcela =  parcelaRepository.save(parcela);
        return this.modelMapper.map(parcela, ParcelaResponseDTO.class);

    }
}
