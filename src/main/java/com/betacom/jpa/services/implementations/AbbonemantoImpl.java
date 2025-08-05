package com.betacom.jpa.services.implementations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.dto.RicevutaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import com.betacom.jpa.services.interfaces.IMessaggioServices;
import com.betacom.jpa.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AbbonemantoImpl extends Utilities  implements IAbbonamentoServices{

	private IAbbonamentoRepository abboR;
	private ISocioRepository socioR;
	private IMessaggioServices msgS;
	
	public AbbonemantoImpl(IAbbonamentoRepository abboR, ISocioRepository socioR, IMessaggioServices msgS) {
		this.abboR = abboR;
		this.socioR = socioR;
		this.msgS = msgS;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(AbbonamentoReq req) throws AcademyException {
		log.debug("create :" + req);
		Optional<Socio> s = socioR.findById(req.getSocioId());
		if (s.isEmpty()) 
			throw new AcademyException(msgS.getMessaggio("socio-not-exist"));
		if (req.getDataIscrizione() == null)
			throw new AcademyException(msgS.getMessaggio("certif-invalid-date"));
		
		Abbonamento abbo = new Abbonamento();
		abbo.setDataIscrizione(req.getDataIscrizione());
		abbo.setSocio(s.get());
		
		abboR.save(abbo);
	}
	
	@Override
	public AbbonamentoDTO getById(Integer id) throws AcademyException {
		Optional<Abbonamento> ab = abboR.findById(id);
		if (ab.isEmpty())
			throw new AcademyException(msgS.getMessaggio("no-abbo"));
		
		Abbonamento a = ab.get();
		
		return AbbonamentoDTO.builder()
				.id(a.getId())
				.dataIscrizione(a.getDataIscrizione())
				.attivita(buildAttivita(a.getAttivita()))
				.build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void remove(AbbonamentoReq req) throws AcademyException {
		Optional<Abbonamento> ab = abboR.findById(req.getId());
		if (ab.isEmpty())
			throw new AcademyException(msgS.getMessaggio("no-abbo"));
				
		if (!ab.get().getAttivita().isEmpty()) {
			ab.get().getAttivita().removeAll(ab.get().getAttivita());
			abboR.save(ab.get());
		}

		abboR.delete(ab.get());
	}

	@Override
	public RicevutaDTO buildRicevuta(Integer idAbbonamento) throws AcademyException {
		log.debug("buildRicevuta :" + idAbbonamento);
		Optional<Abbonamento> a = abboR.findById(idAbbonamento);
		if (a.isEmpty())
			throw new AcademyException(msgS.getMessaggio("no-abbo"));
		Abbonamento ab = a.get();
		if (ab.getAttivita().isEmpty())
			throw new AcademyException(msgS.getMessaggio("no-attiv"));
		
		RicevutaDTO ric = new RicevutaDTO();
		ric.setCognome(ab.getSocio().getCognome());
		ric.setNome(ab.getSocio().getNome());
		
		ric.setRiga( ab.getAttivita().stream()
			        .map(at ->AttivitaDTO.builder()
			        	.id(at.getId())
			        	.descrizione(at.getDescrizione())
			        	.prezzo(at.getPrezzo())
			        	.build()
			       ).toList());
			
		ric.setTotale(ab.getAttivita().stream()
			    .map(Attivita::getPrezzo)
			    .reduce(BigDecimal.ZERO, BigDecimal::add));

		return ric;
	}

	@Override
	public List<AbbonamentoDTO> getAbbonamentiBySocio(Integer id) throws AcademyException {
		Optional<Socio> soc = socioR.findById(id);
		if (soc.isEmpty())
			throw new AcademyException(msgS.getMessaggio("socio-not-exist"));
		List<Abbonamento> lA = soc.get().getAbbonamento();
		return lA.stream()
				.map(a -> AbbonamentoDTO.builder()
						.id(a.getId())
						.dataIscrizione(a.getDataIscrizione())
						.attivita(buildAttivita(a.getAttivita()))
						.build()
						).toList();
	}



}
