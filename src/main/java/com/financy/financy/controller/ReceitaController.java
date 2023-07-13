package com.financy.financy.controller;

import com.financy.financy.requestDTO.ReceitaRequestDTO;
import com.financy.financy.responseDTO.ReceitaResponseDTO;
import com.financy.financy.service.ReceitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping
    public ResponseEntity<ReceitaResponseDTO> salvar(@RequestBody ReceitaRequestDTO receitaRequestDTO){
        ReceitaResponseDTO receitaResponseDTO = this.receitaService.salvar(receitaRequestDTO);
        return ResponseEntity.ok(receitaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReceitaResponseDTO>> buscarTodasReceitas(){
        List<ReceitaResponseDTO> receitas = this.receitaService.buscarTodas();
        return ResponseEntity.ok(receitas);
    }

}
