package com.financy.financy.repository;

import com.financy.financy.entity.Divida;
import com.financy.financy.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financy.financy.entity.Parcela;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {


    @Query("SELECT SUM(par.valorParcela) FROM Parcela par WHERE par.status = :status")
    Double sumParcelas(@Param(value = "status") Status status);


}
