package com.financy.financy.repository;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.financy.financy.entity.Divida;
import com.financy.financy.enums.Status;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Long> {
    @Query("SELECT div FROM Divida div WHERE div.status = :status ")
    List<Divida> findByStatusAberto(@Param(value = "status") Status status);

    @Query("SELECT div FROM Divida div WHERE div.status = :status ")
    List<Divida> findByStatusPago(@Param(value = "status") Status status);

    @Query("SELECT SUM(div.valor) FROM Divida div WHERE div.status = :status")
    Double sumDividaAberta(@Param(value = "status") Status status);



}
