package com.financy.financy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.financy.financy.entity.Responsavel;
import com.financy.financy.repository.ResponsavelRepository;
import com.financy.financy.requestDTO.ResponsavelRequestDTO;
import com.financy.financy.responseDTO.ResponsavelResponseDTO;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final ModelMapper modelMapper;

    public ResponsavelService(ResponsavelRepository responsavelRepository, ModelMapper modelMapper) {
        this.responsavelRepository = responsavelRepository;
        this.modelMapper = modelMapper;
    }

    public ResponsavelResponseDTO salvar(ResponsavelRequestDTO responsavelRequestDTO) {
        Responsavel responsavel = this.modelMapper.map(responsavelRequestDTO, Responsavel.class);
        responsavel = this.responsavelRepository.save(responsavel);
        return this.modelMapper.map(responsavel, ResponsavelResponseDTO.class);
    }

    public List<ResponsavelResponseDTO> findAll() {
        return this.responsavelRepository.findAll().stream().map(responsaveis -> {
            return this.modelMapper.map(responsaveis, ResponsavelResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public ResponsavelResponseDTO findById(Long id) {
        Responsavel responsavel = this.responsavelRepository.findById(id).get();
        return this.modelMapper.map(responsavel, ResponsavelResponseDTO.class);
    }

    public Responsavel findByIdResponsavel(Long id) {
        try {
            Responsavel credor = this.responsavelRepository.findById(id).get();
            return credor;
        } catch (Exception e) {
            throw new RuntimeException("Responsavel não encontrado");
        }
    }
}
