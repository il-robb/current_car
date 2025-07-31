package com.betacom.jpa.reporitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, String>{
	
	Optional<Categoria> findByDescrizione(String descrizione);


}
