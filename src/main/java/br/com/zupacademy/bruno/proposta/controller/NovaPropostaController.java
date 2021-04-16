package br.com.zupacademy.bruno.proposta.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<URI> novaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder){
		Proposta novaProposta = request.toModel();
		em.persist(novaProposta);
		URI uri = builder.path("/propostas/{id}").build(novaProposta.getId());
		return ResponseEntity.created(uri).build();
	}
	
	
}

