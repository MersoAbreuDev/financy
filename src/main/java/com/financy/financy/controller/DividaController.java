package com.financy.financy.controller;

import java.util.List;

import com.financy.financy.responseDTO.DividaResumeResponseDTO;
import com.financy.financy.responseDTO.ParcelaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.financy.financy.enums.Status;
import com.financy.financy.requestDTO.DividaRequestDTO;
import com.financy.financy.responseDTO.DividaResponseDTO;
import com.financy.financy.service.DividaService;

@RequestMapping("/dividas")
@RestController
public class DividaController {

    private final DividaService dividaService;

    public DividaController(DividaService dividaService) {
        this.dividaService = dividaService;
    }

    @PostMapping
    public ResponseEntity<DividaResponseDTO> save(@RequestBody DividaRequestDTO dividaRequestDTO) {
        DividaResponseDTO dividaResponseDTO = this.dividaService.save(dividaRequestDTO);
        return ResponseEntity.ok().body(dividaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DividaResumeResponseDTO>> getAll() {
        List<DividaResumeResponseDTO> dividas = this.dividaService.findAll();
        return ResponseEntity.ok().body(dividas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DividaResponseDTO> payUpdate(@PathVariable("id") Long id) {
        DividaResponseDTO dividaResponseDTO = this.dividaService.udateDivida(id);
        return ResponseEntity.ok().body(dividaResponseDTO);
    }

    @GetMapping("/busca-aberto")
    public ResponseEntity<List<DividaResponseDTO>> getByPayOpen(Status status) {
        List<DividaResponseDTO> dividasResponseDTO = this.dividaService
                .findByStatusOpen(status);
        return ResponseEntity.ok().body(dividasResponseDTO);
    }

    @GetMapping("/busca-pago")
    public ResponseEntity<List<DividaResponseDTO>> getByPayClose() {

        List<DividaResponseDTO> dividasResponseDTO = this.dividaService
                .findByStatusClose(Status.PAGO);
        return ResponseEntity.ok().body(dividasResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DividaResponseDTO> getById(@PathVariable("id") Long id) {
        DividaResponseDTO dividaResponseDTO = this.dividaService.buscaPorId(id);
        return ResponseEntity.ok().body(dividaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        this.dividaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/valor-total-dividas")
    public ResponseEntity<Double> somaDividasAberta(Status status) {
        return ResponseEntity.ok().body(this.dividaService.totalDividaAberta(status));
    }

    @GetMapping("/{id}/parcelas")
    public ResponseEntity<List<ParcelaResponseDTO>> pagarParcelaDivida(@PathVariable("id") Long id){
        List<ParcelaResponseDTO> parcelas = this.dividaService.findByIdDividaParcelas(id);
       return ResponseEntity.ok().body(parcelas);
    }

    @PostMapping("/relatorio/{id}")
    public ResponseEntity<DividaResponseDTO> gerarRelatorioPDF(@PathVariable("id") Long id){

        return ResponseEntity.ok().body(this.dividaService.gerarPDF(id));
    }

}
