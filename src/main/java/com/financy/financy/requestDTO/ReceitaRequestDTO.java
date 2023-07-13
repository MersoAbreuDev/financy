package com.financy.financy.requestDTO;

import com.financy.financy.enums.TipoReceita;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaRequestDTO {

    private Long idReceita;

    private String nomeReceita;

    private TipoReceita tipoReceita;

    private BigDecimal valorReceita;

    private LocalDate dataCriacao;

    private String periodoVigente;

    private String observacao;

}
