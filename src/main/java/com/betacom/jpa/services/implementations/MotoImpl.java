package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.MotoDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Moto;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.reporitories.IMotoRepository;
import com.betacom.jpa.reporitories.IVeicoloRepository;
import com.betacom.jpa.request.MotoReq;
import com.betacom.jpa.services.interfaces.ImotoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MotoImpl implements ImotoService{

	private IMotoRepository motoR;
	private IVeicoloRepository veiR;
	
	
	public MotoImpl(IMotoRepository motoR, IVeicoloRepository veiR) {
		super();
		this.motoR = motoR;
		this.veiR = veiR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(MotoReq mReq) throws AcademyException {
		Optional<Moto> m = motoR.findByTarga(mReq.getTarga());
		if(m.isPresent()) throw new AcademyException("moto con targa :"+mReq.getTarga()+" è gia esistente ");
		
		Moto c = new Moto();
		c.setCilindrata(mReq.getCilindrata());
		c.setTarga(mReq.getTarga());
		
		motoR.save(c);
	}
	
	@Override
	public List<VeicoloDTO> listAllMoto() throws AcademyException{
		List<Veicolo> lV = veiR.findAll();
		return lV.stream()
				.filter(a->a.getTipoVeicolo().getDescrizione().equalsIgnoreCase("macchina"))
				.map(b->VeicoloDTO.builder()
						.idVeicolo(b.getIdVeicolo())
						.alimentazione(b.getAlimentazione().getDescrizione())
						.categoria(b.getCategoria().getDescrizione())
						.colore(b.getColore().getDescrizione())
						.marca(b.getMarca().getDescrizione())
						.tipoVeicolo(b.getTipoVeicolo().getDescrizione())
						.numeroRuote(b.getNumeroRuote())
						.annoProduzione(b.getAnnoProduzione())
						.modello(b.getModello())
						.moto(buildMotoDTO(b.getMoto()))
						.bici(null)
						.macchina(null)
						.build()
						).collect(Collectors.toList());
	}
	private MotoDTO buildMotoDTO(Moto b)
	{
		return MotoDTO.builder()
				.cilindrata(b.getCilindrata())
				.idMoto(b.getIdMoto())
				.targa(b.getTarga()).build();
	}

}
