package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import com.betacom.jpa.utilities.Utilities;

@Service
public class AbbonamentoImpl extends Utilities implements IAbbonamentoServices{

	private IAbbonamentoRepository abboR;
	private ISocioRepository socioR;

	public AbbonamentoImpl(IAbbonamentoRepository abboR, ISocioRepository socioR) {
		this.abboR = abboR;
		this.socioR = socioR;
	}

	@Override
	public void create(AbbonamentoReq req) throws AcademyException {
		Optional<Socio> s = socioR.findById(req.getSocioId());
		if (s.isEmpty())
			throw new AcademyException("Socio non presente id database:" + req.getSocioId());
		if (req.getDataIscrizione() == null)
			throw new AcademyException("Data iscrizione non presente");
		
		Abbonamento abbo = new Abbonamento();
		abbo.setDataIscrizione(req.getDataIscrizione());
		abbo.setSocio(s.get());
		
		abboR.save(abbo);
	}


	@Override
	public void update(AbbonamentoReq req) throws AcademyException {
		Optional<Abbonamento> s = abboR.findById(req.getId());
	    if (s.isEmpty()) {
			throw new AcademyException("certificato non trovato in database : " + req.getId());
		}

        Abbonamento cer = s.get();
        if(req.getDataIscrizione() != null) {
            cer.setDataIscrizione(req.getDataIscrizione());
        }

        if(req.getSocioId() != null) {
            Optional<Socio> sCF = socioR.findById(req.getSocioId());
            if (sCF.isEmpty()) {
                throw new AcademyException("Id del socio inesistente ");
                }
            cer.setSocio(socioR.getById(req.getSocioId()));
        }

        abboR.save(cer);
    }



	@Override
	public List<AbbonamentoDTO> listAll() {
		List<Abbonamento> lC = abboR.findAll();
		return lC.stream()
				.map(c -> AbbonamentoDTO.builder()
						.id(c.getSocio().getId())
						.dataIscrizione(c.getDataIscrizione())
						.build()).collect(Collectors.toList());


	}

	@Override
	public AbbonamentoDTO getById(Integer id) throws AcademyException {
		Optional<Abbonamento> a = abboR.findById(id);
		if(a.isEmpty()) throw new AcademyException("abb non esistente ");
		
		return AbbonamentoDTO.builder()
				.id(a.get().getId()).dataIscrizione(a.get().getDataIscrizione()).attivita(buildAttivita(a.get().getAttivita())).build();
	}


}
