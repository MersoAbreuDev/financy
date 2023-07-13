package com.financy.financy.controller;

import com.financy.financy.enums.Status;
import com.financy.financy.responseDTO.ParcelaResponseDTO;
import com.financy.financy.service.ParcelaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

    public final ParcelaService parcelaService;

    public ParcelaController(ParcelaService parcelaService){
        this.parcelaService = parcelaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelaResponseDTO> pagarParcela(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.parcelaService.pagarParcela(id));
    }

    @GetMapping("/busca-valor-parcelas")
    public ResponseEntity<Double> somaParcelasPorStatus(Status status) {
        return ResponseEntity.ok().body(this.parcelaService.totalParcelas(status));
    }


}
