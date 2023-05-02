package com.financy.financy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financy.financy.entity.Credor;

@Repository
public interface CredorRepository extends JpaRepository<Credor, Long> {

}
