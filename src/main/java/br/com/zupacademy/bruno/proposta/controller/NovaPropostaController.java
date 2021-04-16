package br.com.zupacademy.bruno.proposta.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.proposta.controller.model.Proposta;
import br.com.zupacademy.bruno.proposta.controller.request.NovaPropostaRequest;

@RestController
@RequestMapping
public class NovaPropostaController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder){
		Proposta novaProposta = request.toModel();
		List<?> resultList = em.createQuery("SELECT 1 FROM Proposta WHERE documento = :documento").setParameter("documento", request.getDocumento()).getResultList();
		if(!resultList.isEmpty()){
			return ResponseEntity.unprocessableEntity().body("Proposta já cadastrada.");
		}
		em.persist(novaProposta);
		URI uri = builder.path("/propostas/{id}").build(novaProposta.getId());
		return ResponseEntity.created(uri).build();
	}
	
	
}

