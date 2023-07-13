package com.financy.financy.requestDTO.security;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{Cpf é obrigatório}")
    private String cpf;

    public String get() {
        return cpf;
    }

    public void setCpfRa(String cpfRa) {
        this.cpf = cpf;
    }
}