package com.financy.financy.service.security;

import com.financy.financy.entity.security.Usuario;
import com.financy.financy.enums.Role;
import com.financy.financy.repository.security.UsuarioRepository;
import com.financy.financy.requestDTO.security.AuthenticationRequestDTO;
import com.financy.financy.requestDTO.security.RegisterRequestDTO;
import com.financy.financy.responseDTO.security.AuthenticationResponseDTO;
import com.financy.financy.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationService {

    private final UsuarioRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDTO register(RegisterRequestDTO requestDTO) {
        var usuario = Usuario.builder()
                .cpf(requestDTO.getEmail())
                .senha(passwordEncoder.encode(requestDTO.getSenha()))
                .role(Role.buscarRole(requestDTO.getRole()))
                .build();
        repository.save(usuario);

        var jwtToken = jwtService.generateToken(usuario);
        return  AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getCpfRa(),
                        requestDTO.getSenha()
                )
        );
        var user = repository.findByCpfRa(requestDTO.getCpfRa()).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
        String nome;
        if (user.getCpf().length() == 11) {
            //  Professor professor = this.professorService.buscarOProfessorPeloCpf(usuario.getCpfRa());
            //  nome = professor.getNome();
            nome = "Loko";
        } else {
            //   Aluno aluno = this.alunoService.buscarOAlunoPeloRa(usuario.getCpfRa());
            //  nome = aluno.getNome();

            nome = "louca";
        }
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .nome(nome)
                .build();
    }
}
