package com.financy.financy.requestDTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.financy.financy.enums.Status;

public class DividaRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private LocalDate dataCompra;

    private LocalDate dataVencimento;

    private Double valor;

    private Integer parcela;

    private Status status;

    private Long idCredor;

    private Long idResponsavel;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getParcela() {
        return this.parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getIdCredor() {
        return this.idCredor;
    }

    public void setIdCredor(Long idCredor) {
        this.idCredor = idCredor;
    }

    public Long getIdResponsavel() {
        return this.idResponsavel;
    }

    public void setIdResponsavel(Long idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
