package com.financy.financy.requestDTO.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String role;


}