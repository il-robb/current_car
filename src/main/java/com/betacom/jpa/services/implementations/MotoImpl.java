package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Moto;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.repositories.IAlimentazioneRepository;
import com.betacom.jpa.repositories.IBiciRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.repositories.IColoreRepository;
import com.betacom.jpa.repositories.IMacchinaRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.repositories.IMotoRepository;
import com.betacom.jpa.repositories.ISospensioneRepository;
import com.betacom.jpa.repositories.ITipoVeicoloRepository;
import com.betacom.jpa.repositories.IVeicoloRepository;
import com.betacom.jpa.request.MotoReq;
import com.betacom.jpa.request.VeicoloReq;
import com.betacom.jpa.services.interfaces.IMotoServices;
import com.betacom.jpa.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MotoImpl extends Utilities implements IMotoServices{
	
	private IVeicoloRepository vR;
	private IMacchinaRepository carR;
	private IMotoRepository mR;
	private IBiciRepository bR;
	private IAlimentazioneRepository alimR;
	private ICategoriaRepository catR;
	private IColoreRepository colR;
	private IMarcaRepository marR;
	private ITipoVeicoloRepository tvR;
	private ISospensioneRepository sR;

	

	public MotoImpl(IVeicoloRepository vR, IMacchinaRepository carR, IMotoRepository mR, IBiciRepository bR,
			IAlimentazioneRepository alimR, ICategoriaRepository catR, IColoreRepository colR, IMarcaRepository marR,
			ITipoVeicoloRepository tvR, ISospensioneRepository sR) {
		this.vR = vR;
		this.carR = carR;
		this.mR = mR;
		this.bR = bR;
		this.alimR = alimR;
		this.catR = catR;
		this.colR = colR;
		this.marR = marR;
		this.tvR = tvR;
		this.sR = sR;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(VeicoloReq vreq, MotoReq mreq) throws AcademyException {
		
		Optional<Moto> m = mR.findByTarga(mreq.getTarga());
		
		if(m.isPresent()) {
			throw new AcademyException("Macchina già presente nel database");
		}
		
		Moto moto = new Moto();
		moto.setCilindrata(mreq.getCilindrata());
		moto.setIdMoto(mreq.getIdMoto());
		moto.setTarga(mreq.getTarga());
		mR.save(moto);
		
		VeicoloImpl vImpl = new VeicoloImpl(vR, carR, mR, bR, alimR, catR, colR, marR, tvR, sR);
		
		vImpl.create(vreq);
		
		List<Veicolo> lv = vR.findAll();
		Veicolo v = lv.getLast();
		v.setMoto(mR.findByTarga(mreq.getTarga()).get());
		vR.save(v);
	}
	
	@Override
	public List<VeicoloDTO> listAll() {
		List<Veicolo> lV = vR.findAll();
		
		return lV.stream()
				.filter(v -> v.getTipoVeicolo().getDescrizione().equalsIgnoreCase("macchina"))
				.map(c -> VeicoloDTO.builder()
						.tipoVeicolo(c.getTipoVeicolo().getDescrizione())
						.alimentazione(c.getAlimentazione().getDescrizione())
						.categoria(c.getCategoria().getDescrizione())
						.colore(c.getColore().getDescrizione())
						.marca(c.getMarca().getDescrizione())
						.modello(c.getModello())
						.numeroRuote(c.getNumeroRuote())
						.annoProduzione(c.getAnnoProduzione())
						.macchina(null)
						.moto(builMoto(c.getMoto()))
						.bici(null)
						.build()
						)
				.collect(Collectors.toList());
	}
}
