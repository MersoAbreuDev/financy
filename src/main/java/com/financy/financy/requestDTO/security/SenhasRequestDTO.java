package com.financy.financy.requestDTO.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class SenhasRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Primeira senha não pode ser em branco")
    @NotNull(message = "Primeira é obrigstória")
    private String senha01;

    @NotBlank(message = "Segunda senha não pode ser em branco")
    @NotNull(message = "Segunda senha é obrigstória")
    private String senha02;


    private String cpf;


    public String getCpf() {
        return cpf;
    }

    public void setCpfRa(String cpfRa) {
        this.cpf = cpfRa;
    }

    public String getSenha01() {
        return senha01;
    }

    public void setSenha01(String senha01) {
        this.senha01 = senha01;
    }

    public String getSenha02() {
        return senha02;
    }

    public void setSenha02(String senha02) {
        this.senha02 = senha02;
    }
}