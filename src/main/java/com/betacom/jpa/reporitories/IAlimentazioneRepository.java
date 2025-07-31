package com.betacom.jpa.reporitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Alimentazione;

public interface IAlimentazioneRepository extends JpaRepository<Alimentazione, Integer>{
	
	Optional<Alimentazione> findByDescrizione(String descrizione);

}
