package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Macchina;

public interface IMacchinaRepository extends JpaRepository<Macchina, Integer>{

	Optional<Macchina> findByTarga(String targa);
}
