package com.financy.financy.responseDTO;

import com.financy.financy.enums.TipoReceita;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaResponseDTO {


    private Long idReceita;

    private String nomeReceita;

    private TipoReceita tipoReceita;

    private BigDecimal valorReceita;

    private LocalDate dataCriacao;

    private String periodoVigente;

    private String observacao;
}
