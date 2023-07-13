package com.financy.financy.responseDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.financy.financy.enums.Status;

public class DividaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private LocalDate dataCompra;

    private Double valor;

    private Integer parcela;

    private Double valorParcelas;

    private Status status;

    private CredorResponseDTO credor;

    private ResponsavelResponseDTO responsavel;

    private LocalDate dataVencimento;

    private int codigoParcela;

    private List<ParcelaResponseDTO> parcelas;

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

    public Double getValorParcelas() {
        return this.valorParcelas;
    }

    public void setValorParcelas(Double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CredorResponseDTO getCredor() {
        return this.credor;
    }

    public void setCredor(CredorResponseDTO credor) {
        this.credor = credor;
    }

    public ResponsavelResponseDTO getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(ResponsavelResponseDTO responsavel) {
        this.responsavel = responsavel;
    }

    public List<ParcelaResponseDTO> getParcelas() {
        return this.parcelas;
    }

    public void setParcelas(List<ParcelaResponseDTO> parcelas) {
        this.parcelas = parcelas;
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
