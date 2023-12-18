package com.financy.financy.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.financy.financy.requestDTO.CredorRequestDTO;
import com.financy.financy.responseDTO.CredorResponseDTO;
import com.financy.financy.service.CredorService;

@RequestMapping("/credores")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        CredorResponseDTO credorResponseDTO = this.credorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
