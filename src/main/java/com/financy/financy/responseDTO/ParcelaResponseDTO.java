package com.financy.financy.responseDTO;

import java.io.Serializable;

import com.financy.financy.entity.Divida;
import com.financy.financy.enums.Status;

public class ParcelaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Status status;

    private Double valorParcela;

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

}
