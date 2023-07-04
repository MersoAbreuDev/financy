package com.financy.financy.service;

import com.financy.financy.responseDTO.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.financy.financy.entity.Credor;
import com.financy.financy.entity.Divida;
import com.financy.financy.entity.Parcela;
import com.financy.financy.entity.Responsavel;
import com.financy.financy.enums.Status;
import com.financy.financy.repository.DividaRepository;
import com.financy.financy.requestDTO.DividaRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DividaService {

    private final DividaRepository dividaRepository;
    private final ModelMapper modelMapper;
    private final ParcelaService parcelaService;
    private final CredorService credorService;
    private final ResponsavelService responsavelService;

    public DividaService(DividaRepository dividaRepository,
            ModelMapper modelMapper, ParcelaService parcelaService,
            CredorService credorService, ResponsavelService responsavelService) {
        this.dividaRepository = dividaRepository;
        this.modelMapper = modelMapper;
        this.parcelaService = parcelaService;
        this.credorService = credorService;
        this.responsavelService = responsavelService;
    }

    // Necessita realizar a validação do credor e setar na mão, sem que deixe o
    // model mapper realizar, para o credor e tambem para o responsavel
    public DividaResponseDTO save(DividaRequestDTO dividaRequestDTO) {
        try {

            Credor credor = this.credorService.findByIdCredor(dividaRequestDTO.getIdCredor());
            Responsavel responsavel = this.responsavelService.findByIdResponsavel(dividaRequestDTO.getIdResponsavel());

            Divida divida = this.modelMapper.map(dividaRequestDTO, Divida.class);

            Double valorParcela = this.parcelas(divida.getParcela(), divida.getValor());
            divida.setStatus(Status.ABERTO);
            divida.setResponsavel(responsavel);
            divida.setCredor(credor);
            divida.setValorParcelas(valorParcela);
            divida = this.dividaRepository.save(divida);
            List<Parcela> parcelas = new ArrayList<>();
            for (int i = 0; i < divida.getParcela(); i++) {
                Parcela parcela = new Parcela();

                parcela.setDivida(divida);
                parcela.setStatus(divida.getStatus());
                parcela.setValorParcela(divida.getValorParcelas());
                parcela = this.parcelaService.salvaParcela(parcela);
                parcelas.add(parcela);
            }
            List<ParcelaResponseDTO> parcelasResponseDTO = new ArrayList<>();
            for (Parcela parcela : parcelas) {
                ParcelaResponseDTO par = new ParcelaResponseDTO();
                par.setId(parcela.getId());
                par.setStatus(parcela.getStatus());
                par.setValorParcela(parcela.getValorParcela());
                parcelasResponseDTO.add(par);
            }
            DividaResponseDTO dividaResponseDTO = this.modelMapper.map(divida, DividaResponseDTO.class);
            dividaResponseDTO.setParcelas(parcelasResponseDTO);
            return dividaResponseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Erro");
        }

    }

    public double parcelas(Integer quantidadeParcelas, Double valorCompra) {
        return valorCompra / quantidadeParcelas;
    }

    public List<DividaResumeResponseDTO> findAll() {

        List<Divida> dividas = this.dividaRepository.findAll();
        List<DividaResumeResponseDTO> dividasResponseDTO = new ArrayList<>();

        for (Divida listaDivida : dividas) {
            DividaResumeResponseDTO dividaResponseDTO = new DividaResumeResponseDTO();
              dividaResponseDTO.setId(listaDivida.getId());
              dividaResponseDTO.setNome(listaDivida.getNome());
              dividaResponseDTO.setDataCompra(listaDivida.getDataCompra());
              dividaResponseDTO.setStatus(listaDivida.getStatus());
              dividaResponseDTO.setValor(listaDivida.getValor());
              dividaResponseDTO.setParcela(listaDivida.getParcela());
              dividaResponseDTO.setValorParcelas(listaDivida.getValorParcelas());
              dividasResponseDTO.add(dividaResponseDTO);
        }
        return dividasResponseDTO;
    }

    public DividaResponseDTO udateDivida(Long id) {
        Divida divida = this.dividaRepository.findById(id).get();
        divida.setStatus(Status.PAGO);
        divida = this.dividaRepository.save(divida);
        return this.modelMapper.map(divida, DividaResponseDTO.class);
    }

    public List<DividaResponseDTO> findByStatusOpen(Status status) {
        return this.dividaRepository.findByStatusAberto(status.ABERTO).stream().map(dividas -> {
            return this.modelMapper.map(dividas, DividaResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public List<DividaResponseDTO> findByStatusClose(Status status) {
        return this.dividaRepository.findByStatusPago(status.PAGO).stream().map(dividas -> {
            return this.modelMapper.map(dividas, DividaResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public DividaResponseDTO buscaPorId(Long id) {
        Divida divida = this.dividaRepository.findById(id).get();
        return this.modelMapper.map(divida, DividaResponseDTO.class);
    }

    public void delete(Long id) {
        this.dividaRepository.findById(id).ifPresent(divida -> {
            this.dividaRepository.delete(divida);
        });
    }

    public Double totalDividaAberta(Status status) {
        return this.dividaRepository.sumDividaAberta(status);
    }


    public List<ParcelaResponseDTO> findByIdDividaParcelas(Long id) {
        Divida divida = this.dividaRepository.findById(id).get();
        List<ParcelaResponseDTO> parcelaResponseDTOS = divida.getParcelas().stream()
                .map(parcela -> modelMapper.map(parcela, ParcelaResponseDTO.class))
                .collect(Collectors.toList());
        return parcelaResponseDTOS;
    }

}
