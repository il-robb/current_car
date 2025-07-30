package com.betacom.jpa.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Utilities {
	
	
//	public List<AbbonamentoDTO> buildAbbonamento(List<Abbonamento> ab){
//		return ab.stream()
//				.map(a -> AbbonamentoDTO.builder()
//						.id(a.getId())
//						.dataIscrizione(a.getDataIscrizione())
//						.attivita(buildAttivita(a.getAttivita()))
//						.build()					
//						)
//				.collect(Collectors.toList());
//	}
//	
//	public List<AttivitaDTO>  buildAttivita(List<Attivita> at){
//		return at.stream()
//				.map(a-> AttivitaDTO.builder()
//						.id(a.getId())
//						.descrizione(a.getDescrizione())
//						.prezzo(a.getPrezzo())
//						.build()
//						)
//				.collect(Collectors.toList());
//	}
//	
//	public List<SocioDTO> buildListSocioDTO(List<Socio> lS){
//		
//		return lS.stream()
//				.map(s -> SocioDTO.builder()
//						.codiceFiscale(s.getCodiceFiscale())
//						.id(s.getId())
//						.cognome(s.getCognome())
//						.nome(s.getNome())
//						.email(s.getEmail())
//						.certificato((s.getCertificato() == null) ? null : CertificatoDTO.builder()
//								.id(s.getCertificato().getId())
//								.tipo(s.getCertificato().getTipo())
//								.dataCertificato(s.getCertificato().getDataCertificato())
//								.build()
//								)
//						.abbonamento(buildAbbonamento(s.getAbbonamento()))
//						.build())
//				.collect(Collectors.toList());
//		
//	}
//	
//	public BigDecimal totalPayment(List<Abbonamento> lA) {
//		BigDecimal tot= BigDecimal.ZERO;
//		for (Abbonamento abb : lA) {
//			for (Attivita att : abb.getAttivita()) {
//				tot = tot.add(att.getPrezzo());
//			}
//		}
//		return tot;
//	}
//	
//	public List<AttivitaDTO> listaAttivita(List<Abbonamento> lA) {
//	    List<AttivitaDTO> lAttivita = new ArrayList<AttivitaDTO>();
//	    for (Abbonamento a : lA) {
//	        for (Attivita att : a.getAttivita()) {
//	            lAttivita.add(AttivitaDTO.builder()
//	                .id(att.getId())
//	                .descrizione(att.getDescrizione())
//	                .prezzo(att.getPrezzo())
//	                .build());
//	        }
//	    }
//	    return lAttivita.stream().distinct().toList();
//	}
}
