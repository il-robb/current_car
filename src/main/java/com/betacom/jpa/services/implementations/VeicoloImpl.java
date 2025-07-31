package com.betacom.jpa.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.TipoVeicolo;
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
import com.betacom.jpa.request.MacchinaReq;
import com.betacom.jpa.request.VeicoloReq;
import com.betacom.jpa.services.interfaces.IVeicoloServices;
import com.betacom.jpa.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VeicoloImpl extends Utilities implements IVeicoloServices{


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

	

	public VeicoloImpl(IVeicoloRepository vR, IMacchinaRepository carR, IMotoRepository mR, IBiciRepository bR,
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


	
	@Override
	public List<VeicoloDTO> listAll() throws AcademyException{
		List<VeicoloDTO> lTot = new ArrayList<VeicoloDTO>();
		MacchinaImpl c = new MacchinaImpl(vR, carR, mR, bR, alimR, catR, colR, marR, tvR, sR);
		MotoImpl m = new MotoImpl(vR, carR, mR, bR, alimR, catR, colR, marR, tvR, sR);
		BiciImpl b = new BiciImpl(vR, carR, mR, bR, alimR, catR, colR, marR, tvR, sR);
		
		lTot.addAll(c.listAll());
		lTot.addAll(m.listAll());
		lTot.addAll(b.listAll());
		
		return lTot;
	}
	
	@Override
	public void addAlimentazione(String descrizione, String tipoDiVeicolo) throws AcademyException {
		Optional<TipoVeicolo> a = tvR.findByDescrizione(tipoDiVeicolo);
		if(a.isEmpty()) throw new AcademyException("tipo di veicolo non valido :"+tipoDiVeicolo);
		
		Optional<Alimentazione> b = alimR.findByDescrizione(descrizione);
		if(b.isEmpty()) throw new AcademyException("tipo di alimentazione non esistente: "+descrizione);
		
		TipoVeicolo tv = a.get();
		if(tv.getAlimentazione().contains(b.get()))throw new AcademyException("alimentazione :"+descrizione+" gia esistente in :"+tipoDiVeicolo);
		
		tv.getAlimentazione().add(b.get());
		
		tvR.save(tv);
		
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(VeicoloReq vreq) throws AcademyException {
		Veicolo v = new Veicolo();
		
		if(tvR.findByDescrizione(vreq.getTipoVeicolo()).isPresent()) {

            v.setTipoVeicolo(tvR.findByDescrizione(vreq.getTipoVeicolo()).get());

            if(alimR.findByDescrizione(vreq.getAlimentazione()).isPresent()) {
            	if(v.getTipoVeicolo().getAlimentazione().contains(alimR.findByDescrizione(vreq.getAlimentazione()).get())) {
            		 v.setAlimentazione(alimR.findByDescrizione(vreq.getAlimentazione()).get());
            	}else {
                    throw new AcademyException("alimentazione non valida per il tipo di veicolo");
            	}
            }else {
            	throw new AcademyException("tipo veicolo non valida");
            }   
        }else {
            throw new AcademyException("tipo veicolo non valido");
        }
		if(catR.findByDescrizione(vreq.getCategoria()).isPresent()) {
			v.setCategoria(catR.findByDescrizione(vreq.getCategoria()).get());
		}else {
			throw new AcademyException("Categoria non valida");
		}
		if(colR.findByDescrizione(vreq.getColore()).isPresent()) {
			v.setColore(colR.findByDescrizione(vreq.getColore()).get());
		}else {
			throw new AcademyException("Colore non valido");
		}
		if(marR.findByDescrizione(vreq.getMarca()).isPresent()) {
			v.setMarca(marR.findByDescrizione(vreq.getMarca()).get());
		}else {
			throw new AcademyException("Marca non valida");
		}
		if(marR.findByDescrizione(vreq.getModello()).isPresent()) {
			v.setMarca(marR.findByDescrizione(vreq.getMarca()).get());
		}else {
			throw new AcademyException("Modello non valida");
		}
		v.setNumeroRuote(vreq.getNumeroRuote());
		v.setAnnoProduzione(vreq.getAnnoProduzione());
		v.setModello(vreq.getModello());
	
		vR.save(v);
	}
	
	
	
	
	
	
	
	
	
	
	
}
