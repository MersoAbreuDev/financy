package com.financy.financy.service;
import com.financy.financy.responseDTO.DividaResponseDTO;
import com.financy.financy.responseDTO.DividaResumeResponseDTO;
import com.financy.financy.responseDTO.ParcelaResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.financy.financy.entity.Credor;
import com.financy.financy.entity.Divida;
import com.financy.financy.entity.Parcela;
import com.financy.financy.entity.Responsavel;
import com.financy.financy.enums.Status;
import com.financy.financy.repository.DividaRepository;
import com.financy.financy.requestDTO.DividaRequestDTO;

import java.time.LocalDate;
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

    private final EntityManager entityManager;

    public DividaService(DividaRepository dividaRepository,
                         ModelMapper modelMapper, ParcelaService parcelaService,
                         CredorService credorService, ResponsavelService responsavelService,
                         EntityManager entityManager) {
        this.dividaRepository = dividaRepository;
        this.modelMapper = modelMapper;
        this.parcelaService = parcelaService;
        this.credorService = credorService;
        this.responsavelService = responsavelService;
        this.entityManager = entityManager;
    }

    // Necessita realizar a validação do credor e setar na mão, sem que deixe o
    // model mapper realizar, para o credor e tambem para o responsavel
    public DividaResponseDTO save(DividaRequestDTO dividaRequestDTO) {
        try {
            Credor credor = credorService.findByIdCredor(dividaRequestDTO.getIdCredor());
            Responsavel responsavel = responsavelService.findByIdResponsavel(dividaRequestDTO.getIdResponsavel());
            Divida divida = modelMapper.map(dividaRequestDTO, Divida.class);

            Double valorParcela = parcelas(divida.getParcela(), divida.getValor());
            divida.setStatus(Status.ABERTO);
            divida.setDataCompra(divida.getDataCompra());
            divida.setResponsavel(responsavel);
            divida.setCredor(credor);
            divida.setValorParcelas(valorParcela);
            divida = dividaRepository.save(divida);

            List<Parcela> parcelas = new ArrayList<>();
            if(divida.getParcela() == 1){
                for (int i = 0; i < divida.getParcela(); i++) {
                    Parcela parcela = new Parcela();
                    parcela.setDivida(divida);
                    parcela.setStatus(divida.getStatus());
                    parcela.setCodigoParcela(i + 1);
                    parcela.setValorParcela(divida.getValorParcelas());
                    parcela.setDataVencimento(divida.getDataVencimento());
                    parcela = parcelaService.salvaParcela(parcela);
                    parcelas.add(parcela);
                }
            }else{
                LocalDate dataVencimento = divida.getDataCompra().plusMonths(1);
                for (int i = 0; i < divida.getParcela(); i++) {
                    Parcela parcela = new Parcela();
                    parcela.setDivida(divida);
                    parcela.setStatus(divida.getStatus());
                    parcela.setCodigoParcela(i + 1);
                    parcela.setValorParcela(divida.getValorParcelas());
                    parcela.setDataVencimento(((LocalDate) dataVencimento).plusMonths(i));
                    parcela = parcelaService.salvaParcela(parcela);
                    parcelas.add(parcela);
                }

            }

            List<ParcelaResponseDTO> parcelasResponseDTO = new ArrayList<>();
            for (Parcela parcela : parcelas) {
                ParcelaResponseDTO par = new ParcelaResponseDTO();
                par.setId(parcela.getId());
                par.setStatus(parcela.getStatus());
                par.setDataVencimento(parcela.getDataVencimento());
                par.setCodigoParcela(parcela.getCodigoParcela());
                par.setValorParcela(parcela.getValorParcela());
                parcelasResponseDTO.add(par);
            }

            DividaResponseDTO dividaResponseDTO = modelMapper.map(divida, DividaResponseDTO.class);
            dividaResponseDTO.setParcelas(parcelasResponseDTO);
            return dividaResponseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Erro"+ e);
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
              dividaResponseDTO.setDataVencimento(listaDivida.getDataVencimento());
              dividaResponseDTO.setCredor(listaDivida.getCredor());
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

    public List<Divida> obterDividas() {
        String jpql = "SELECT DISTINCT d FROM Divida d JOIN FETCH d.parcelas";
        TypedQuery<Divida> query = entityManager.createQuery(jpql, Divida.class);
        return query.getResultList();
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

    public void delete(Long id) throws Exception {
        try {
            this.dividaRepository.findById(id).ifPresent(divida -> {
                this.dividaRepository.delete(divida);
            });
        }catch (Exception e){
            throw new RuntimeException("Não possivel realizar a exclusão devido ter Credor e responsável vinculado.");
        }

    }

    public Double totalDividaAberta(Status status) {
        return this.dividaRepository.sumDividaAberta(status);
    }


    public List<ParcelaResponseDTO> findByIdDividaParcelas(Long id) {
        this.verificaParcelasPagas(id);
        Divida divida = this.dividaRepository.findById(id).get();
        List<ParcelaResponseDTO> parcelaResponseDTOS = divida.getParcelas().stream()
                .map(parcela -> modelMapper.map(parcela, ParcelaResponseDTO.class))
                .collect(Collectors.toList());
        return parcelaResponseDTOS;
    }

    public void verificaParcelasPagas(Long id){
        Divida divida = dividaRepository.findById(id).orElse(null);

        if (divida != null) {
            List<Parcela> parcelas = divida.getParcelas();
            boolean todasParcelasPagas = parcelas.stream().allMatch(parcela -> parcela.getStatus() == Status.PAGO);

            if (todasParcelasPagas) {
                divida.setStatus(Status.PAGO);
                dividaRepository.save(divida);
            }
        }
    }

    public void salvarDivida(Divida divida){
        this.dividaRepository.save(divida);
    }

//    @Scheduled(fixedDelay = 60000)
//    public void verificarParcelasPagas() {
//
//        List<Divida> dividas = this.obterDividas();
//
//        for (Divida divida : dividas) {
//
//            boolean todasParcelasPagas = divida.getParcelas().stream()
//                    .allMatch(parcela -> parcela.getStatus().equals("pago"));
//
//            if (todasParcelasPagas) {
//                divida.setStatus(Status.PAGO);
//
//                salvarDivida(divida);
//            }
//        }
//    }

    public DividaResponseDTO gerarPDF(Long id) {
        DividaResponseDTO dividaResponseDTO = this.buscaPorId(id);
        Divida divida = new Divida();
        divida.setStatus(dividaResponseDTO.getStatus());
        return dividaResponseDTO;
    }
}
