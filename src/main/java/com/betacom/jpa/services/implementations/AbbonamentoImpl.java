package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.IAbbonamentiRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import com.betacom.jpa.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AbbonamentoImpl extends Utilities implements IAbbonamentoServices{
	
	private IAbbonamentiRepository abboR;
	private ISocioRepository socR;
	
	public AbbonamentoImpl(IAbbonamentiRepository abboR, ISocioRepository socR) {
		super();
		this.abboR = abboR;
		this.socR = socR;
	}
	/*
	 * @Transactional
	 * Indica che il metodo (o la classe) è soggetto a una transazione: cioè tutte le operazioni di database 
	 * dentro quel metodo sono eseguite come un'unica unità di lavoro. Se qualcosa va storto, tutto viene annullato (rollback).
	 * 
	 * rollbackFor = Exception.class
	 * Questa parte dice: “Se viene sollevata una qualsiasi eccezione, anche non controllata, allora fai 
	 * il rollback della transazione.”
	 * Rollback significa annullare tutte le modifiche fatte al database durante la transazione.
	 * Di default, Spring fa rollback solo per RuntimeException, ma qui stai dicendo: “Fai rollback anche per le Exception normali.”
	 * 
	 * propagation = Propagation.REQUIRED
	 * Specifica come deve comportarsi la transazione quando chiami questo metodo:
	 * REQUIRED è il valore predefinito e significa:
	 * Se esiste già una transazione, usala.
	 * Se non esiste, creane una nuova.
	 */
	
	//@Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORT)
	/*
	 * Propagation.SUPPORTS
	 * Se esiste già una transazione, il metodo la utilizzerà.
	 * Se non esiste alcuna transazione, il metodo continuerà comunque, senza aprirne una nuova.
	 * È utile per metodi che possono funzionare sia dentro che fuori da una transazione. Ad esempio, metodi 
	 * di sola lettura (come quelli che leggono dal database senza modificarlo), dove non è fondamentale avere una transazione attiva.
	 * 
	 * @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
	 * Propagation.MANDATORY
	 * Se esiste già una transazione, il metodo la userà.
	 * Se non esiste, invece di crearne una nuova... lancia un'eccezione (IllegalTransactionStateException)!
	 * È utile quando vuoi obbligare il chiamante a gestire la transazione. In pratica, stai dicendo:
	 * “Questo metodo deve essere chiamato dentro una transazione, altrimenti fallisce.”
	 * 
	 * @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
	 * Se c’è già una transazione, Spring ne crea una "nested" (annidata).
	 * Se non c’è una transazione attiva, funziona come REQUIRED e ne apre una nuova.
	 * La transazione nested è collegata alla principale, ma può avere rollback solo sul suo blocco, usando savepoint del database.
	 * Se succede qualcosa di grave nella transazione figlia, puoi annullarla senza dover annullare tutta la principale!
	 */
	
	
	/*
	 * isolation = Isolation.DEFAULT è una configurazione dell'annotazione @Transactional in Spring 
	 * che riguarda il livello di isolamento della transazione.
	 * 
	 * Il livello di isolamento determina come una transazione interagisce con le altre che accedono
	 * contemporaneamente agli stessi dati. Serve a prevenire problemi come:
	 * Dirty Read: leggere dati non ancora confermati
	 * Non-repeatable Read: leggere due volte lo stesso dato e ottenere risultati diversi
	 * Phantom Read: leggere un insieme di righe e poi vederne di nuove dopo un’altra transazione
	 * 
	 * Isolation.DEFAULT
	 * Questo valore indica che Spring non forza un livello di isolamento:
	 * Usa quello predefinito del database sottostante.
	 * Esempi:
	 * MySQL → usa REPEATABLE READ
	 * PostgreSQL → usa READ COMMITTED
	 * Oracle → usa READ COMMITTED
	 * Quando è OK usare DEFAULT?
	 * Quando non hai esigenze particolari sul controllo della concorrenza
	 * Quando ti fidi del comportamento predefinito del tuo database
	 * Se vuoi garantire una consistenza specifica tra transazioni
	 * 
	 * @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	 * Isolation.SERIALIZABLE è il livello di isolamento più alto disponibile in Spring (e nei database in generale). 
	 * Garantisce che la transazione sia completamente isolata da tutte le altre in esecuzione contemporaneamente.
	 * Impone un comportamento come se le transazioni fossero eseguite una alla volta, in ordine.
	 * Evita tutti i problemi di concorrenza:
	 * Dirty Read 🚫
	 * Non-repeatable Read 🚫
	 * Phantom Read 🚫
	 * In pratica: leggi solo dati confermati, e nessun altro può modificare o aggiungere righe che influenzino 
	 * il tuo risultato fino alla fine della tua transazione.
	 * 
	 * Controindicazioni
	 * Prestazioni più lente → Il database deve bloccare più righe/tabelle e quindi riduce la concorrenza.
	 * Non tutti i DB gestiscono SERIALIZABLE allo stesso modo. Alcuni simulano il comportamento tramite lock espliciti.
	 * 
	 * @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.REPETABLE_READ)
	 * Isolation.REPEATABLE_READ è uno dei livelli di isolamento più utili quando vuoi evitare letture incoerenti, 
	 * ma senza sacrificare troppa performance.
	 * Cos'è Isolation.REPEATABLE_READ?
	 * Impedisce le non-repeatable reads, cioè:
	 * Protegge da:
	 * Dirty Read → leggere dati non confermati
	 * Non-repeatable Read → riga modificata tra due letture
	 * NON protegge da:
	 * Phantom Read → nuove righe visibili in una seconda query
	 * Quando usarlo?
	 * Quando leggi righe che non devono cambiare durante l’intera transazione
	 * Se vuoi più consistenza rispetto a READ COMMITTED 
	 * Se ti servono query aggregate bloccate contro nuovi inserimenti → meglio SERIALIZABLE
	 * 
	 * @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	 * Isolation.READ_COMMITTED è probabilmente il livello di isolamento più usato e anche quello di default 
	 * in diversi database relazionali (come PostgreSQL e Oracle). 
	 * Cos'è Isolation.READ_COMMITTED?
	 * Permette di leggere solo dati già confermati da altre transazioni.
	 * Impedisce i dirty read (cioè la lettura di dati che potrebbero essere annullati da una transazione non ancora conclusa).
	 * Non protegge da:
	 * Non-repeatable read → se leggi la stessa riga due volte, il valore potrebbe essere diverso se un'altra transazione 
	 * l'ha modificata nel frattempo.
	 * Phantom read → potresti vedere nuove righe in una seconda query aggregate all'interno della stessa transazione.
	 * Quando usarlo?
	 * Ottimo per la maggior parte delle operazioni di scrittura
	 * Utile se vuoi un compromesso tra consistenza e performance
	 * Evitalo se devi fare analisi aggregate stabili o vuoi rileggere lo stesso dato con certezza
	 */
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public void create(AbbonamentoReq req) throws AcademyException {
		log.debug("Create: " +req);
		Optional<Socio> s = socR.findById(req.getSocioId());
		if (s.isEmpty()) {
			throw new AcademyException("Socio non presente nel db: " +req.getSocioId());
		}
		if (req.getDataIscrizione() == null) {
			throw new AcademyException("Data iscrizione non presente nel db");
		}
		Abbonamento abb = new Abbonamento();
		abb.setDataIscrizione(req.getDataIscrizione());
		abb.setSocio(s.get()); //carico completamente il socio
		
		abboR.save(abb);
	}

	@Override
	public AbbonamentoDTO getById(Integer id) throws AcademyException{
		Optional<Abbonamento> ab = abboR.findById(id);
		if (ab.isEmpty()) {
			throw new AcademyException("Abbonamento non presente nel db: " +id);
		}
		Abbonamento a = ab.get();
		return AbbonamentoDTO.builder()
									   .id(a.getId())
									   .dataIscrizione(a.getDataIscrizione())
									   .attivita(buildAttivita(a.getAttivita()))
									   .build();
	}

	@Override
	public void delete(AbbonamentoReq req) throws AcademyException {
		log.debug("Delete: " + req);
		Optional<Abbonamento> ab = abboR.findById(req.getId());
		if (ab.isEmpty()) {
			throw new AcademyException("Abbonamento non trovato: " + req.getId());
		}
		if (ab.get().getAttivita().isEmpty()) {
			abboR.delete(ab.get());
		} else {
			throw new AcademyException("Impossibile eliminare abbonamento, attività presenti in questo abbonamento");
		}
	}
	
}
