package com.financy.financy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.financy.financy.entity.Credor;
import com.financy.financy.repository.CredorRepository;
import com.financy.financy.requestDTO.CredorRequestDTO;
import com.financy.financy.responseDTO.CredorResponseDTO;

@Service
public class CredorService {

    private final CredorRepository credorRepository;

    private final ModelMapper modelMapper;

    public CredorService(CredorRepository credorRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.credorRepository = credorRepository;

    }

    public CredorResponseDTO save(CredorRequestDTO credorRequestDTO) {
        Credor credor = this.modelMapper.map(credorRequestDTO, Credor.class);
        credor = this.credorRepository.save(credor);
        CredorResponseDTO credorResponseDTO = this.modelMapper.map(credor, CredorResponseDTO.class);
        return credorResponseDTO;

    }

    public List<CredorResponseDTO> listAll() {
        return this.credorRepository.findAll().stream().map(clientes -> {
            return this.modelMapper.map(clientes, CredorResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public CredorResponseDTO findbyId(Long id) {
        Credor credor = this.credorRepository.findById(id).get();
        CredorResponseDTO credorResponseDTO = this.modelMapper.map(credor, CredorResponseDTO.class);
        return credorResponseDTO;

    }

    public Credor findByIdCredor(Long id) {
        try {
            Credor credor = this.credorRepository.findById(id).get();
            return credor;
        } catch (Exception e) {
            throw new RuntimeException("Credor não encontrado");
        }
    }

}
