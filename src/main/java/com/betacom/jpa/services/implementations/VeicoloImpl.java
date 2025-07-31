package com.betacom.jpa.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
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

	
	
	public VeicoloImpl(IVeicoloRepository veiR, IMotoRepository motoR, IMacchinaRepository carR, IBiciRepository biciR,
				IAlimentazioneRepository aliR, ICategoriaRepository catR, IColoreRepository colR, IMarcaRepository marcaR,
				ITipoVeicoloRepository tipoR, ISospensioneRepository sospR) {
			super();
			this.veiR = veiR;
			this.motoR = motoR;
			this.carR = carR;
			this.biciR = biciR;
			this.aliR = aliR;
			this.catR = catR;
			this.colR = colR;
			this.marcaR = marcaR;
			this.tipoR = tipoR;
			this.sospR = sospR;
		}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(VeicoloReq vreq) throws AcademyException {
	Veicolo v = new Veicolo();

	if(tipoR.findByDescrizione(vreq.getTipoVeicolo()).isPresent()) {

	            v.setTipoVeicolo(tipoR.findByDescrizione(vreq.getTipoVeicolo()).get());

	            if(aliR.findByDescrizione(vreq.getAlimentazione()).isPresent()) {
	            	
	            	if(v.getTipoVeicolo().getAlimentazione().contains(aliR.findByDescrizione(vreq.getAlimentazione()).get())) {
	             
	            		v.setAlimentazione(aliR.findByDescrizione(vreq.getAlimentazione()).get());
	            	}else {
	                    throw new AcademyException("alimentazione non valida per il tipo di veicolo");
	            	}
	            }else{
	            	throw new AcademyException("alimentazione non valida");
	            }
	            if(catR.findByDescrizione(vreq.getCategoria()).isPresent()) {
	            	
	            	if(v.getTipoVeicolo().getCategoria().contains(catR.findByDescrizione(vreq.getCategoria()).get())) {
	             
	            		v.setCategoria(catR.findByDescrizione(vreq.getCategoria()).get());
	            	}else {
	                    throw new AcademyException("categoria non valida per il tipo di veicolo");
	            	}
	            }else{
	            	throw new AcademyException("categoria non valida");
	            }
	 }else{
		 throw new AcademyException("tipo veicolo non valido");
	 }
	if(colR.findByDescrizione(vreq.getColore()).isPresent()) {
	v.setColore(colR.findByDescrizione(vreq.getColore()).get());
	}else {
	throw new AcademyException("Colore non valido");
	}
	if(marcaR.findByDescrizione(vreq.getMarca()).isPresent()) {
	v.setMarca(marcaR.findByDescrizione(vreq.getMarca()).get());
	}else {
	throw new AcademyException("Marca non valida");
	}
	
	
	
	v.setNumeroRuote(vreq.getNumeroRuote());
	v.setAnnoProduzione(vreq.getAnnoProduzione());
	v.setModello(vreq.getModello());

	veiR.save(v);
	}
	
	@Override
	public List<VeicoloDTO> listAll() throws AcademyException {
		List<VeicoloDTO> lTot =new ArrayList<VeicoloDTO>();
		MacchinaImpl mI = new MacchinaImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		lTot.addAll(mI.listAllCar());

		MotoImpl motoI = new MotoImpl(motoR,veiR);
		lTot.addAll(motoI.listAllMoto());
		
		BiciImpl bI = new BiciImpl(biciR,veiR,sospR);
		lTot.addAll(bI.listAllBici());
		
		return lTot;
	}

	
	
	
}
