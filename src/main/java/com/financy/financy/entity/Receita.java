package com.financy.financy.entity;


import com.financy.financy.enums.TipoReceita;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceita;

    private String nomeReceita;

    private TipoReceita tipoReceita;

    private BigDecimal valorReceita;

    @CreationTimestamp
    private LocalDate dataCriacao;

    private String periodoVigente;

    private String observacao;



}
