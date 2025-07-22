package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Certificato;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ICertificatoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.services.interfaces.ICertificatoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CertificatoImpl implements ICertificatoService {

	private ICertificatoRepository certR;
	private ISocioRepository socioR;

	public CertificatoImpl(ICertificatoRepository certR, ISocioRepository socioR) {
		this.certR = certR;
		this.socioR = socioR;
	}

	@Override
	public void insert(CertificatoReq req) throws AcademyException {
		log.debug("Create: " + req);
		Optional<Socio> soc = socioR.findById(req.getSocioId());
		if (soc.isEmpty())
			throw new AcademyException("Socio non trovato: " + req.getSocioId());

		Certificato certificato = new Certificato();
		certificato.setDataCertificato(req.getDataCertificato());
		certificato.setTipo(req.getTipo());
		certificato.setSocio(soc.get());

		certR.save(certificato);
	}

	@Override
	public List<SocioDTO> listAll() throws AcademyException {
		List<Certificato> lC = certR.findAll();

		return lC.stream()
				.map(c -> new SocioDTO(c.getSocio().getId(), c.getSocio().getCognome(), c.getSocio().getNome(),
						c.getSocio().getCodiceFiscale(), c.getSocio().getEmail(),
						new CertificatoDTO(c.getId(), c.getTipo(), c.getDataCertificato())))
				.collect(Collectors.toList());
	}

}
