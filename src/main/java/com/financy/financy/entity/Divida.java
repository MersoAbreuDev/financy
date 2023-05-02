package com.financy.financy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.financy.financy.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Divida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @CreationTimestamp
    private LocalDate dataCompra;

    private Double valor;

    private Integer parcela;

    private Double valorParcelas;

    @OneToMany(mappedBy = "divida")
    private List<Parcela> parcelas;

    @ManyToOne
    @JoinColumn(name = "credor")
    private Credor credor;

    @ManyToOne
    @JoinColumn(name = "responsavel")
    private Responsavel responsavel;

    private Status status;

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

    public Credor getCredor() {
        return this.credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public Responsavel getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    // public List<Parcela> getParcelas() {
    // return this.parcelas;
    // }

    // public void setParcelas(List<Parcela> parcelas) {
    // this.parcelas = parcelas;
    // }

}
