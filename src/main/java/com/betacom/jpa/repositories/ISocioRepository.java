package com.betacom.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Socio;

@Repository
public interface ISocioRepository extends JpaRepository<Socio, Integer>{
	
	Optional<Socio> findByCodiceFiscale(String codiceFiscale);
	
	List<Socio> findByCognomeAndNome(String cognome, String nome);
	
	@Query(name="socio.selectByAttivita")
	List<Socio> searchByAttivita(@Param("attivita") String attivita);
	
	@Query(name="socio.selectByFilter")
	List<Socio> searchByFilter(
			@Param("id") Integer id,
			@Param("nome") String nome,
			@Param("cognome") String cognome,
			@Param("attivita") String attivita
			);

}
