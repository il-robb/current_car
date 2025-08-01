package com.betacom.jpa.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messaggi_system")

public class Messaggio {
	@EmbeddedId
	private MessaggioId msgId;
	private String messaggio;
}
