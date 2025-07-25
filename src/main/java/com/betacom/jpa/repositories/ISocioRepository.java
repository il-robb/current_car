package com.betacom.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Socio;

@Repository
public interface ISocioRepository extends JpaRepository<Socio, Integer>{
	
	Optional<Socio> findByCodiceFiscale(String codiceFiscale);
	List<Socio> findByCognomeAndNome(String cognome, String nome);

}
