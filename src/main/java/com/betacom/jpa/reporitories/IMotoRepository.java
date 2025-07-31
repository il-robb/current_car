package com.betacom.jpa.reporitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Moto;

public interface IMotoRepository extends JpaRepository<Moto, Integer>{
	Optional<Moto> findByTarga(String targa);
}
