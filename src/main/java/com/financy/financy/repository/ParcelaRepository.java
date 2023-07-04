package com.financy.financy.repository;

import com.financy.financy.entity.Divida;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financy.financy.entity.Parcela;

import java.util.Optional;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

}
