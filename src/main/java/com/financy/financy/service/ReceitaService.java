package com.financy.financy.service;

import com.financy.financy.entity.Receita;
import com.financy.financy.repository.ReceitaRepository;
import com.financy.financy.requestDTO.ReceitaRequestDTO;
import com.financy.financy.responseDTO.ReceitaResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    private ReceitaRepository receitaRepository;

    private ModelMapper modelMapper;

    public ReceitaService(ReceitaRepository receitaRepository,
                          ModelMapper modelMapper) {
        this.receitaRepository = receitaRepository;
        this.modelMapper = modelMapper;
    }

    public ReceitaResponseDTO salvar(ReceitaRequestDTO receitaRequestDTO) {
        Receita receita = new Receita();
        receita.setNomeReceita(receitaRequestDTO.getNomeReceita());
        receita.setValorReceita(receitaRequestDTO.getValorReceita());
        receita.setTipoReceita(receitaRequestDTO.getTipoReceita());
        receita.setObservacao(receitaRequestDTO.getObservacao());
        receita.setPeriodoVigente(receitaRequestDTO.getPeriodoVigente());
        receita = this.receitaRepository.save(receita);
        ReceitaResponseDTO receitaResponseDTO = new ReceitaResponseDTO();
        receitaResponseDTO.setIdReceita(receita.getIdReceita());
        receitaResponseDTO.setDataCriacao(receita.getDataCriacao());
        receitaResponseDTO.setObservacao(receita.getObservacao());
        receitaResponseDTO.setPeriodoVigente(receita.getPeriodoVigente());
        receitaResponseDTO.setValorReceita(receita.getValorReceita());
        receitaResponseDTO.setTipoReceita(receita.getTipoReceita());
        receitaResponseDTO.setNomeReceita(receita.getNomeReceita());
        return receitaResponseDTO;
    }

    public List<ReceitaResponseDTO> buscarTodas() {
        return this.receitaRepository.findAll().stream().map(receitas -> {
            return this.modelMapper.map(receitas, ReceitaResponseDTO.class);
        }).collect(Collectors.toList());
    }


}
