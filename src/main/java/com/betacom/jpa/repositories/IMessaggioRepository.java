package com.betacom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Messaggio;
import com.betacom.jpa.models.MessaggioId;

public interface IMessaggioRepository extends JpaRepository<Messaggio, MessaggioId>{
	
}
