package com.betacom.jpa.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Veicolo;

public interface IVeicoloRepository extends JpaRepository<Veicolo, Integer> {

}
