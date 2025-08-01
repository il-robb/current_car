package com.betacom.ju;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (name = "nome", nullable = false)
	private String nome;
	
	@Column (name = "cognome", nullable = false)
	private String cognome;
	
	@Column (name = "email", nullable = false)
	private String email;
}
