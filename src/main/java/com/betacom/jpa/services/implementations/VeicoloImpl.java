package com.betacom.jpa.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.TipoVeicolo;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.reporitories.IAlimentazioneRepository;
import com.betacom.jpa.reporitories.IBiciRepository;
import com.betacom.jpa.reporitories.ICategoriaRepository;
import com.betacom.jpa.reporitories.IColoreRepository;
import com.betacom.jpa.reporitories.IMacchinaRepository;
import com.betacom.jpa.reporitories.IMarcaRepository;
import com.betacom.jpa.reporitories.IMotoRepository;
import com.betacom.jpa.reporitories.ISospensioneRepository;
import com.betacom.jpa.reporitories.ITipoVeicoloRepository;
import com.betacom.jpa.reporitories.IVeicoloRepository;
import com.betacom.jpa.request.BiciReq;
import com.betacom.jpa.request.MacchinaReq;
import com.betacom.jpa.request.MotoReq;
import com.betacom.jpa.request.VeicoloReq;
import com.betacom.jpa.services.interfaces.IVeicoloServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VeicoloImpl implements IVeicoloServices{

	
	private IVeicoloRepository veiR;
	private IMotoRepository motoR;
	private IMacchinaRepository carR;
	private IBiciRepository biciR;
	private IAlimentazioneRepository aliR;
	private ICategoriaRepository catR;
	private IColoreRepository colR;
	private IMarcaRepository marcaR;
	private ITipoVeicoloRepository tipoR;
	private ISospensioneRepository sospR;

	public VeicoloImpl(IVeicoloRepository veiR, IMacchinaRepository carR, IAlimentazioneRepository aliR,
			ICategoriaRepository catR, IColoreRepository colR, IMarcaRepository marcaR, ITipoVeicoloRepository tipoR, MacchinaImpl macchinaImpl) {
		super();
		this.veiR = veiR;
		this.carR = carR;
		this.aliR = aliR;
		this.catR = catR;
		this.colR = colR;
		this.marcaR = marcaR;
		this.tipoR = tipoR;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(VeicoloReq vReq, MacchinaReq carReq,MotoReq mReq,BiciReq bReq) throws AcademyException {
		Optional<Macchina> m = carR.findByTarga(carReq.getTarga());
		if(m.isPresent()) throw new AcademyException("macchina con targa :"+carReq.getTarga()+" è gia esistente ");
		
		Macchina c = new Macchina();
		c.setCilindrata(carReq.getCilindrata());
		c.setNumeroPorte(carReq.getNumeroPorte());
		c.setTarga(carReq.getTarga());
		
		carR.save(c);
		
		Veicolo v = new Veicolo();
		if(aliR.findByDescrizione(vReq.getAlimentazione()).isPresent()) 
			v.setAlimentazione(aliR.findByDescrizione(vReq.getAlimentazione()).get());
		else
			throw new AcademyException("alimentazione non valida");
		
		if(catR.findByDescrizione(vReq.getCategoria()).isPresent()) 
			v.setCategoria(catR.findByDescrizione(vReq.getCategoria()).get());	
		else
			throw new AcademyException("categoria non valida");

		if(colR.findByDescrizione(vReq.getColore()).isPresent()) 
			v.setColore(colR.findByDescrizione(vReq.getColore()).get());
		else
			throw new AcademyException("colore non valido");

		if(marcaR.findByDescrizione(vReq.getMarca()).isPresent()) 
			v.setMarca(marcaR.findByDescrizione(vReq.getMarca()).get());
		else
			throw new AcademyException("colore non valido");

		if(tipoR.findByDescrizione(vReq.getTipoVeicolo()).isPresent()) 
			v.setTipoVeicolo(tipoR.findByDescrizione(vReq.getTipoVeicolo()).get());
		else
			throw new AcademyException("colore non valido");
		
		
		v.setNumeroRuote(vReq.getNumeroRuote());
		v.setAnnoProduzione(vReq.getAnnoProduzione());
		v.setModello(vReq.getModello());
		
		switch (v.getTipoVeicolo().getDescrizione()) {
		
		case "macchina": {
			MacchinaImpl x = new MacchinaImpl(carR,veiR);
			x.create(carReq);
			v.setMacchina(carR.findByTarga(carReq.getTarga()).get());
			break;
		}
		case "moto":{
			MotoImpl x = new MotoImpl(motoR,veiR);
			x.create(mReq);
			v.setMoto(motoR.findByTarga(mReq.getTarga()).get());
			break;
			
		}
		case "bici":{
			BiciImpl x = new BiciImpl(biciR,veiR,sospR);
			x.create(bReq);
			List<Bici> lB = biciR.findAll();
			v.setBici(lB.getLast());
			break;
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + v.getTipoVeicolo());
		}
		
		veiR.save(v);
	}

	@Override
	public List<VeicoloDTO> listAll() throws AcademyException {
		List<VeicoloDTO> lTot =new ArrayList<VeicoloDTO>();
		MacchinaImpl mI = new MacchinaImpl(carR,veiR);
		lTot.addAll(mI.listAllCar());

		MotoImpl motoI = new MotoImpl(motoR,veiR);
		lTot.addAll(motoI.listAllMoto());
		
		BiciImpl bI = new BiciImpl(biciR,veiR,sospR);
		lTot.addAll(bI.listAllBici());
		
		return lTot;
	}

	@Override
	public void addAlimentazione(String descrizione, String tipoDiVeicolo) throws AcademyException {
		Optional<TipoVeicolo> a = tipoR.findByDescrizione(tipoDiVeicolo);
		if(a.isEmpty()) throw new AcademyException("tipo di veicolo non valido :"+tipoDiVeicolo);
		
		Optional<Alimentazione> b = aliR.findByDescrizione(descrizione);
		if(b.isEmpty()) throw new AcademyException("tipo di alimentazione non esistente: "+descrizione);
		
		TipoVeicolo tv = a.get();
		if(tv.getAlimentazione().contains(b.get()))throw new AcademyException("alimentazione :"+descrizione+" gia esistente in :"+tipoDiVeicolo);
		
		tv.getAlimentazione().add(b.get());
		
		tipoR.save(tv);
		
	}
	
	
	
}
