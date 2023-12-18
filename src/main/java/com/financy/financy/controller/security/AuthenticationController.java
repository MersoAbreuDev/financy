package com.financy.financy.controller.security;

import com.financy.financy.requestDTO.security.AuthenticationRequestDTO;
import com.financy.financy.requestDTO.security.RegisterRequestDTO;
import com.financy.financy.responseDTO.security.AuthenticationResponseDTO;
import com.financy.financy.service.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller", description = "APIs relacionadas à autenticação")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Cadastrar Usuario", description = "Endpoint para cadastrar um novo usuário")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO requestDTO) {
        return ResponseEntity.ok(service.register(requestDTO));
    }

    @PostMapping("/authenticate")

    @Operation(summary = "Autenticar Usuario e gerar o token", description = "Endpoint para autenticar o usuário e gerar o toekn")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO requestDTO) {
        return ResponseEntity.ok(service.authenticate(requestDTO));
    }
}
