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
import com.betacom.jpa.request.MotoReq;
import com.betacom.jpa.request.VeicoloReq;
import com.betacom.jpa.services.interfaces.ImotoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MotoImpl implements ImotoService{

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

	

	public MotoImpl(IVeicoloRepository veiR, IMotoRepository motoR, IMacchinaRepository carR, IBiciRepository biciR,
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


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(MotoReq mReq, VeicoloReq veiReq) throws AcademyException {
		Optional<Moto> m = motoR.findByTarga(mReq.getTarga());
		if(m.isPresent()) throw new AcademyException("moto con targa :"+mReq.getTarga()+" è gia esistente ");
		
		Moto c = new Moto();
		c.setCilindrata(mReq.getCilindrata());
		c.setTarga(mReq.getTarga());
		Integer id =motoR.save(c).getIdMoto();
		c.setIdMoto(id);
		
		VeicoloImpl veiImpl= new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.create(veiReq);
		
		List<Veicolo> lv = veiR.findAll();
        Veicolo v = lv.getLast();
        c.setVeicolo(v);
        motoR.save(c);
        v.setMoto(c);
        veiR.save(v);
		
		
		;
	}
	
	@Override
	public List<VeicoloDTO> listAllMoto() throws AcademyException{
		List<Veicolo> lV = veiR.findAllByDescrizione("moto");
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
