package com.financy.financy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financy.financy.requestDTO.CredorRequestDTO;
import com.financy.financy.responseDTO.CredorResponseDTO;
import com.financy.financy.service.CredorService;

@RequestMapping("/credores")
@RestController
public class CredorController {

    private final CredorService credorService;

    public CredorController(CredorService credorService) {
        this.credorService = credorService;
    }

    @PostMapping
    public ResponseEntity<CredorResponseDTO> salvar(@RequestBody CredorRequestDTO credorRequestDTO) {
        CredorResponseDTO credorResponseDTO = this.credorService.save(credorRequestDTO);
        return ResponseEntity.ok().body(credorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CredorResponseDTO>> getAll() {
        List<CredorResponseDTO> credoresResponseDTOs = this.credorService.listAll();
        return ResponseEntity.ok().body(credoresResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredorResponseDTO> getById(@PathVariable("id") Long id) {
        CredorResponseDTO credorResponseDTO = this.credorService.findbyId(id);
        return ResponseEntity.ok().body(credorResponseDTO);
    }

}
