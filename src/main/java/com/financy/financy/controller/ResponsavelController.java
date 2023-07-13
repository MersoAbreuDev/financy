package com.financy.financy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financy.financy.requestDTO.ResponsavelRequestDTO;
import com.financy.financy.responseDTO.ResponsavelResponseDTO;
import com.financy.financy.service.ResponsavelService;

@RequestMapping("/responsaveis")
@RestController
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @PostMapping
    public ResponseEntity<ResponsavelResponseDTO> save(@RequestBody ResponsavelRequestDTO responsavelRequestDTO) {
        ResponsavelResponseDTO responsavelResponseDTO = this.responsavelService.salvar(responsavelRequestDTO);
        return ResponseEntity.ok().body(responsavelResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelResponseDTO>> getAll() {
        List<ResponsavelResponseDTO> responsaveis = this.responsavelService.findAll();
        return ResponseEntity.ok().body(responsaveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelResponseDTO> getById(@PathVariable("id") Long id) {
        ResponsavelResponseDTO responsavelResponseDTO = this.responsavelService.findById(id);
        return ResponseEntity.ok().body(responsavelResponseDTO);
    }
}
