package com.financy.financy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.financy.financy.enums.Status;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

@Entity
public class Parcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Status status;

    private int codigoParcela;

    private LocalDate dataVencimento;

    private Double valorParcela;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "divida")
    private Divida divida;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getValorParcela() {
        return this.valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Divida getDivida() {
        return this.divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getCodigoParcela() {
        return codigoParcela;
    }

    public void setCodigoParcela(int codigoParcela) {
        this.codigoParcela = codigoParcela;
    }
}
