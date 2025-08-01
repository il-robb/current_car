package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.reporitories.IMarcaRepository;
import com.betacom.jpa.request.MarcaReq;
import com.betacom.jpa.services.interfaces.IMarcaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MarcaImpl implements IMarcaService{

	private IMarcaRepository mR;
	
	public MarcaImpl(IMarcaRepository mR) {
		this.mR = mR;
	}

	@Override
	public List<MarcaDTO> listAll() throws AcademyException {
		List<Marca> lM = mR.findAll();
		return lM.stream()
				.map(m -> MarcaDTO.builder()
						.idMarca(m.getIdMarca())
						.descrizione(m.getDescrizione())
						.build()
						)
				.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MarcaReq markreq) throws AcademyException {
		log.debug("Create Marca");
		Optional<Marca> m = mR.findByDescrizione(markreq.getDescrizione());
		if(m.isPresent()) {
			throw new AcademyException("Tipo Veicolo già presente");
		}
		
		Marca mark = new Marca();
		if(markreq.getDescrizione() == null) {
			throw new AcademyException("Descrizione obbligatoria");
		}
		mark.setDescrizione(markreq.getDescrizione());
		mR.save(mark);
		
	}

}