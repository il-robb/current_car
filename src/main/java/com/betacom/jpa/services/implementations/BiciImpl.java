package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.BiciDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.reporitories.IBiciRepository;
import com.betacom.jpa.reporitories.ISospensioneRepository;
import com.betacom.jpa.reporitories.IVeicoloRepository;
import com.betacom.jpa.request.BiciReq;
import com.betacom.jpa.services.interfaces.IBiciService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BiciImpl implements IBiciService{
	private IBiciRepository biciR;
	private IVeicoloRepository veiR;
	private ISospensioneRepository sospR;
	


	public BiciImpl(IBiciRepository biciR, IVeicoloRepository veiR, ISospensioneRepository sospR) {
		super();
		this.biciR = biciR;
		this.veiR = veiR;
		this.sospR = sospR;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(BiciReq biciReq) throws AcademyException {
		Bici c = new Bici();
		c.setNumeroMarce(biciReq.getNumeroMarce());
		c.setPieghevole(biciReq.getPieghevole());
		c.setSospensione(sospR.findByDescrizione(biciReq.getSospensione()).get());
		
		biciR.save(c);
	}
	@Override
	public List<VeicoloDTO> listAllBici() throws AcademyException{
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
						.bici(buildBiciDTO(b.getBici()))
						.macchina(null)
						.build()
						).collect(Collectors.toList());
	}
	private BiciDTO buildBiciDTO(Bici b)
	{
		return BiciDTO.builder()
				.numeroMarce(b.getNumeroMarce())
				.pieghevole(b.getPieghevole())
				.sospensione(b.getSospensione().getDescrizione())
				.build();
	}
}
