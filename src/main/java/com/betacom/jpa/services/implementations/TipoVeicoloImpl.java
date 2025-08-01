package com.betacom.jpa.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.TipoVeicolo;
import com.betacom.jpa.reporitories.IAlimentazioneRepository;
import com.betacom.jpa.reporitories.ICategoriaRepository;
import com.betacom.jpa.reporitories.ITipoVeicoloRepository;
import com.betacom.jpa.request.AlimentazioneReq;
import com.betacom.jpa.request.CategoriaReq;
import com.betacom.jpa.request.TipoVeicoloReq;
import com.betacom.jpa.services.interfaces.ITipoVeicoloService;

@Service
public class TipoVeicoloImpl implements ITipoVeicoloService{

	private ITipoVeicoloRepository tipoR;
	private IAlimentazioneRepository aliR;
	private ICategoriaRepository cateR;

	
	
	public TipoVeicoloImpl(ITipoVeicoloRepository tipoR, IAlimentazioneRepository aliR, ICategoriaRepository cateR) {
		super();
		this.tipoR = tipoR;
		this.aliR = aliR;
		this.cateR = cateR;
	}

	@Override
	public void createAlimentazioneTipoVeicolo(AlimentazioneReq aliReq) throws AcademyException {
		Optional<TipoVeicolo> tp = tipoR.findByDescrizione(aliReq.getTipoveicolo());
		if(tp.isEmpty()) throw new AcademyException("tipo veicolo non accettabile "+aliReq.getTipoveicolo());
		Optional<Alimentazione> al= aliR.findByDescrizione(aliReq.getDescrizione());
		if(al.isEmpty()) throw new AcademyException("tipo alimentazione non accettabile "+aliReq.getDescrizione());
		tp.get().getAlimentazione().add(al.get());
		tipoR.save(tp.get());
		
		
	}
	
	@Override
	public void collegaCategoriaTipoVeicolo(CategoriaReq cateReq) throws AcademyException {
		Optional<TipoVeicolo> tp = tipoR.findByDescrizione(cateReq.getDescrizioneVeicolo());
		if(tp.isEmpty()) throw new AcademyException("tipo veicolo non accettabile "+cateReq.getDescrizioneVeicolo());
		Optional<Categoria> al= cateR.findByDescrizione(cateReq.getDescrizione());
		if(al.isEmpty()) throw new AcademyException("tipo alimentazione non accettabile "+cateReq.getDescrizione());
		tp.get().getCategoria().add(al.get());
		tipoR.save(tp.get());
		
		
	}

	@Override
	public void create(TipoVeicoloReq tipoReq) throws AcademyException {
		Optional<TipoVeicolo> tv = tipoR.findByDescrizione(tipoReq.getDescrizione());
		if(tv.isPresent()) throw new AcademyException("tipo veicolo gia esistente "+tipoReq.getDescrizione());
		
		TipoVeicolo tipov = new TipoVeicolo();
		tipov.setDescrizione(tipoReq.getDescrizione());
		
		tipoR.save(tipov);
		
	}

	@Override
	public List<TipoVeicoloDTO> listall() {
		List<TipoVeicolo> lTV = tipoR.findAll();
		return lTV.stream().map(a->TipoVeicoloDTO.builder()
				.idTipoVeicolo(a.getIdTipoVeicolo())
				.descrizione(a.getDescrizione())
				.categoria(categoriaDTOFromList(a.getCategoria()))						
				.alimentazione(alimentazioneDTOFromList(a.getAlimentazione()))
				.build()).collect(Collectors.toList());
	}
	
	private List<CategoriaDTO> categoriaDTOFromList(List<Categoria> lC)
	{
		List<CategoriaDTO> lCDTO = new ArrayList<CategoriaDTO>();
		for(Categoria c : lC)
		{
			lCDTO.add(CategoriaDTO.builder()
					.idCategoria(c.getIdCategoria())
					.descrizione(c.getDescrizione())
					.build());
		}
		return lCDTO;
	}
	
	private List<AlimentazioneDTO> alimentazioneDTOFromList(List<Alimentazione> lC)
	{
		List<AlimentazioneDTO> lCDTO = new ArrayList<AlimentazioneDTO>();
		for(Alimentazione c : lC)
		{
			lCDTO.add(AlimentazioneDTO.builder()
					.idAlimentazione(c.getIdAlimentazione())
					.descrizione(c.getDescrizione())
					.build());
		}
		return lCDTO;
	}

}
