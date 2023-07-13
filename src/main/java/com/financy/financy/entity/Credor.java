package com.financy.financy.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Credor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "credor",cascade = CascadeType.ALL)
    private List<Divida> dividas;

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

}
