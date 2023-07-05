package com.financy.financy.requestDTO.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String cpfRa;

    private  String senha;

    public String getCpfRa() {
        return cpfRa;
    }

    public void setCpfRa(String cpfRa) {
        this.cpfRa = cpfRa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}