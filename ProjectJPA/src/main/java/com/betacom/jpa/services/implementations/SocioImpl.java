package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ISocioService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SocioImpl implements ISocioService {

	private ISocioRepository socioRepo;

	public SocioImpl(ISocioRepository socioRepo) {
		this.socioRepo = socioRepo;
	}

//	@Autowired
//	private ISocioRepository socioRepo;  
	/*
	 * questa è la stessa cosa che scrivere un costruttore
	 * SocioImpl(ISocioRepository socioRepo) { ... } MA IN QUESTO CASO è PIù
	 * OTTIMIZZATO
	 */

	@Override
	public Integer insert(SocioReq req) throws AcademyException {
		log.debug("Insert: " + req);
		Socio socio = new Socio();
		Optional<Socio> s = socioRepo.findByCodiceFiscale(req.getCodiceFiscale());
		if (s.isPresent())
			throw new AcademyException("Socio esistente in DB");

		socio.setCodiceFiscale(req.getCodiceFiscale());
		socio.setCognome(req.getCognome());
		socio.setEmail(req.getEmail());
		socio.setNome(req.getNome());

		return socioRepo.save(socio).getId();

	}

	@Override
	public List<SocioDTO> listAll() throws AcademyException {
		List<Socio> lS = socioRepo.findAll();
		if (lS.isEmpty())
			throw new AcademyException("Non è stato possibile stampare gli elementi");
		return lS.stream()
				.map(s -> new SocioDTO(s.getId(), s.getCognome(), s.getNome(), s.getCodiceFiscale(), s.getEmail(),
						new CertificatoDTO(s.getCertificato().getId(), s.getCertificato().getTipo(),
								s.getCertificato().getDataCertificato())))
				.collect(Collectors.toList());
	}
	
}
