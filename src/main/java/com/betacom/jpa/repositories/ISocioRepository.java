package com.betacom.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.dto.FatturaDTO;
import com.betacom.jpa.models.Socio;

@Repository //non necessario dalla nuova versione
public interface ISocioRepository extends JpaRepository<Socio, Integer>{
	
	Optional<Socio>	findByCodiceFiscale(String codiceFiscale);
	//List<Socio> findByCognomeNome(String cognome, String nome);
	
	@Query(name = "socio.selectByAttivita") //qua potrei mettere direttamente una query
	List<Socio> searchByAttivita(@Param("attivita") String attivita); //definisco una query
	
	@Query(name = "socio.selectByFilter")
	List<Socio> searchByFilter(
							   @Param("id") Integer id,
							   @Param("cognome") String cognome,
							   @Param("nome") String nome,
							   @Param("attivita") String attivita); 
	
	@Query(name = "socio.fattura")
	List<Socio> spesa();

							   
	
}

/*
 * Una Repository è un componente che gestisce l’accesso ai dati.
 * È il ponte tra la tua logica di business (servizi) e il database.
 * 
 * Fa parte del Spring Data JPA
 * È un’interfaccia che fornisce metodi per leggere, scrivere, aggiornare e cancellare dati da una tabella del database
 * Può essere estesa da JpaRepository, CrudRepository o PagingAndSortingRepository
 */