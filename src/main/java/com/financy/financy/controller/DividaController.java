package com.financy.financy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financy.financy.entity.Divida;
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
    public ResponseEntity<List<DividaResponseDTO>> getAll() {
        List<DividaResponseDTO> dividas = this.dividaService.findAll();
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
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.dividaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/valor-total-dividas")
    public ResponseEntity<Double> somaDividasAberta(Status status) {
        Double valorTotal = this.dividaService.totalDividaAberta(status);
        return ResponseEntity.ok().body(valorTotal);
    }

}
