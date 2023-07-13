package com.financy.financy.responseDTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.financy.financy.entity.Divida;
import com.financy.financy.enums.Status;

public class ParcelaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate dataVencimento;

    private Long id;

    private Status status;

    private Double valorParcela;

    private int codigoParcela;



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
