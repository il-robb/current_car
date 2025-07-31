package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.MacchinaDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.reporitories.IMacchinaRepository;
import com.betacom.jpa.reporitories.IVeicoloRepository;
import com.betacom.jpa.request.MacchinaReq;
import com.betacom.jpa.services.interfaces.IMacchinaService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MacchinaImpl implements IMacchinaService{
	
	private IMacchinaRepository carR;
	private IVeicoloRepository veiR;
	

	

	public MacchinaImpl(IMacchinaRepository carR, IVeicoloRepository veiR) {
		super();
		this.carR = carR;
		this.veiR = veiR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(MacchinaReq carReq) throws AcademyException {
		Optional<Macchina> m = carR.findByTarga(carReq.getTarga());
		if(m.isPresent()) throw new AcademyException("macchina con targa :"+carReq.getTarga()+" è gia esistente ");
		
		Macchina c = new Macchina();
		c.setCilindrata(carReq.getCilindrata());
		c.setNumeroPorte(carReq.getNumeroPorte());
		c.setTarga(carReq.getTarga());
		
		carR.save(c);
	}
	
	@Override
	public List<VeicoloDTO> listAllCar() throws AcademyException{
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
						.moto(null)
						.bici(null)
						.macchina(buildMacchinaDTO(b.getMacchina()))
						.build()
						).collect(Collectors.toList());
	}
	private MacchinaDTO buildMacchinaDTO(Macchina b)
	{
		return MacchinaDTO.builder()
				.cilindrata(b.getCilindrata())
				.idMacchina(b.getIdMacchina())
				.numeroPorte(b.getNumeroPorte())
				.targa(b.getTarga()).build();
	}

	

}
