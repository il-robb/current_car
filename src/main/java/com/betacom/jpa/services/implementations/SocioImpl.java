package com.betacom.jpa.services.implementations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.FatturaDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.IMessaggioServices;
import com.betacom.jpa.services.interfaces.ISocioServices;
import com.betacom.jpa.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service

public class SocioImpl extends Utilities implements ISocioServices{

    private final AttivitaImpl attivitaImpl;
	
	private ISocioRepository socioR;
	private IMessaggioServices msgS;
	
	public SocioImpl(ISocioRepository socioR, IMessaggioServices msgS, AttivitaImpl attivitaImpl) {
		this.socioR = socioR;
		this.msgS = msgS;
		this.attivitaImpl = attivitaImpl;
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer insert(SocioReq req) throws AcademyException{
		log.debug("insert: " +req);
		Socio soc = new Socio();
		Optional<Socio> s = socioR.findByCodiceFiscale(req.getCodiceFiscale());
		if (s.isPresent()) {
			throw new AcademyException(msgS.getMessaggio("socio-exists"));
		}
		soc.setCodiceFiscale(req.getCodiceFiscale());
		
		if (req.getCognome() == null) {
			throw new AcademyException("Cognome OBBLIGATORIO");
		}
		if (req.getNome() == null) {
			throw new AcademyException("Nome OBBLIGATORIO");
		}
		soc.setCognome(req.getCognome());
		soc.setEmail(req.getEmail());
		soc.setNome(req.getNome());
		
		return socioR.save(soc).getId();
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SocioReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Socio> s = socioR.findById(req.getId());
		
		if (s.isEmpty()) {
			throw new AcademyException("Socio non trovato nel db: " +req.getId());
		}
		
		Socio soc = s.get();
		if (req.getNome() != null) {
			soc.setNome(req.getNome());
		}
		if (req.getCognome() != null) {
			soc.setCognome(req.getCognome());
		}
		if (req.getEmail() != null) {
			soc.setEmail(req.getEmail());
		}
		if (req.getCodiceFiscale() != null) {
			Optional<Socio> sCF = socioR.findByCodiceFiscale(req.getCodiceFiscale());
			if (sCF.isPresent()) {
				throw new AcademyException("Codice fiscale già esistente sul db");
			}
			soc.setCodiceFiscale(req.getCodiceFiscale());
		}
		socioR.save(soc); //update socio
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(SocioReq req) throws AcademyException {
		log.debug("delete: " + req);
		Optional<Socio> s = socioR.findById(req.getId());
		
		if (s.isEmpty()) {
			throw new AcademyException("Socio non trovato nel db: " +req.getId());
		}
		
		socioR.delete(s.get());
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<SocioDTO> listAll(){
		List<Socio> ls = socioR.findAll();
		return ls.stream()
				.map(s -> SocioDTO.builder()
						.id(s.getId())
						.cognome(s.getCognome())
						.nome(s.getNome())
						.codiceFiscale(s.getCodiceFiscale())
						.email(s.getEmail())
						.certificato((s.getCertificato() == null) ? null : CertificatoDTO.builder()
									.id(s.getCertificato().getId())
									.tipo(s.getCertificato().getTipo())
									.dataCertificato(s.getCertificato().getDataCertificato())
									.build())
						.abbonamento(buildAbbonamento(s.getAbbonamento()))
						.build()).collect(Collectors.toList());
		}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public SocioDTO getSocio(Integer id) throws AcademyException {
		Optional<Socio> s = socioR.findById(id);
		if (s.isEmpty()) {
			throw new AcademyException(msgS.getMessaggio("socio-not-exists"));
		}
		Socio soc = s.get();
		return SocioDTO.builder()
									   .id(soc.getId())
									   .cognome(soc.getCognome())
									   .nome(soc.getNome())
									   .codiceFiscale(soc.getCodiceFiscale())
									   .email(soc.getEmail())
									   .certificato((soc.getCertificato() == null) ? null : CertificatoDTO.builder()
												.id(soc.getCertificato().getId())
												.tipo(soc.getCertificato().getTipo())
												.dataCertificato(soc.getCertificato().getDataCertificato())
												.build())
									   .abbonamento(buildAbbonamento(soc.getAbbonamento()))
									   .build();
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<SocioDTO> listByAttivita(String attivita) {
		log.debug("ListByAttivita: " + attivita);
		List<Socio> lS = socioR.searchByAttivita(attivita);
		return buildListSocioDTO(lS);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<SocioDTO> listByFilter(Integer id, String cognome, String nome, String attivita) {
		log.debug("listByFilter");
		List<Socio> lS = socioR.searchByFilter(id, cognome, nome, attivita);
		return buildListSocioDTO(lS);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<FatturaDTO> generaFatture() {
	    List<Socio> soci = socioR.spesa();
	    return soci.stream().map(socio -> {
	        								List<AttivitaDTO> attivitaTotali = socio.getAbbonamento().stream()
	        								.flatMap(abbonamento -> abbonamento.getAttivita().stream())
	        								//liste di liste, le unisco in un'unica lista.
	        								.map(att -> AttivitaDTO.builder()
	        										.id(att.getId())
	        										.descrizione(att.getDescrizione())
	        										.prezzo(att.getPrezzo())
	        										.build())
	        										.toList();

	        										BigDecimal totalePrezzo = attivitaTotali.stream()
	        										.map(AttivitaDTO::getPrezzo)
	        										.reduce(BigDecimal.ZERO, BigDecimal::add); 
	        										//combino tutti gli elementi in un unico risultato.

	        										return FatturaDTO.builder()
	        												.nome(socio.getNome())
	        												.cognome(socio.getCognome())
	        												.attivita(attivitaTotali)
	        												.totalePrezzoAttivita(totalePrezzo)
	        												.build();
	    									}).toList();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<SocioDTO> spesa() {
		List<Socio> soci = socioR.spesa();

	    return soci.stream()
	        .map(s -> SocioDTO.builder()
	            .id(s.getId())
	            .cognome(s.getCognome())
	            .nome(s.getNome())
	            .codiceFiscale(s.getCodiceFiscale())
	            .email(s.getEmail())
	            .certificato((s.getCertificato() == null) ? null : CertificatoDTO.builder()
	                .id(s.getCertificato().getId())
	                .tipo(s.getCertificato().getTipo())
	                .dataCertificato(s.getCertificato().getDataCertificato())
	                .build())
	            .abbonamento(buildAbbonamento(s.getAbbonamento()))
	            .build())
	        .toList();
	}

}
	
/*
 * @Override
	public List<FatturaDTO> spesaF() {
		log.debug("spesa");
		List<Socio> lS = socioR.spesa();
		System.out.println("Elenco soci: " + lS);
		return lS.stream()
				.map(s -> FatturaDTO.builder()
						.nome(s.getNome())
						.cognome(s.getCognome())
						.attivita(attivitaAbbonamenti(s.getAbbonamento()))
						.totalePrezzoAttivita(totaleAbbonamenti(s.getAbbonamento()))
						.build()).collect(Collectors.toList());
		}
	
	private BigDecimal totaleAbbonamenti(List<Abbonamento> abbonamenti) {
		BigDecimal totale = BigDecimal.ZERO;
		for (Abbonamento abb : abbonamenti) {
			for (Attivita att : abb.getAttivita()) {
				totale = totale.add(att.getPrezzo());
			}
		}
		return totale;
	}
	
	private List <AttivitaDTO> attivitaAbbonamenti(List<Abbonamento> abbonamenti) {
		List <AttivitaDTO> lA = new ArrayList<AttivitaDTO>();
		for (Abbonamento abb : abbonamenti) {
			for (Attivita att : abb.getAttivita()) {
				lA.add(AttivitaDTO.builder().id(abb.getId()).descrizione(att.getDescrizione()).prezzo(att.getPrezzo()).build());
			}
		}
		return lA;
	}
	
 */
	


