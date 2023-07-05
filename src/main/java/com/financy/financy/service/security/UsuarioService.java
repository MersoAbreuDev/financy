package com.financy.financy.service.security;

import com.financy.financy.config.validator.Validador;
import com.financy.financy.repository.security.UsuarioRepository;
import com.financy.financy.security.jwt.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioService {

    private final UsuarioRepository repository;

    private final PasswordEncoder passwordEncod;

    private final ModelMapper modelMapper;

    private final Validador validarCpfECnpjEEmail;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final Validador validador;

    public static final String LINK_TOKEN_RESETAR_SENHA = "http://localhost:8080/crm-moremac/users/reset_password?token=";

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncod, ModelMapper modelMapper, Validador validarCpfECnpjEEmail, Validador validarCpfECnpjEEmail1, JwtService jwtService, AuthenticationService authenticationService, Validador validador) {
        this.repository = repository;
        this.passwordEncod = passwordEncod;
        this.modelMapper = modelMapper;
        this.validarCpfECnpjEEmail = validarCpfECnpjEEmail1;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.validador = validador;
    }
}
