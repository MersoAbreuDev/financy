package com.financy.financy.repository.security;

import com.financy.financy.entity.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
