package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Attivita;

public interface IAttivitaRepository extends JpaRepository<Attivita, Integer>{
	Optional<Attivita> findByDescrizione(String desc);
}
