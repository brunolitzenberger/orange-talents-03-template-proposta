package br.com.zupacademy.bruno.proposta.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.proposta.controller.model.Biometria;
import br.com.zupacademy.bruno.proposta.controller.model.Cartao;
import br.com.zupacademy.bruno.proposta.controller.request.RequestBiometria;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> biometria(@PathVariable String id,
			@RequestBody RequestBiometria request, 
			UriComponentsBuilder uriComponentsBuilder){
		Cartao cartao = em.find(Cartao.class, id);
		if(cartao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado.");
		}
		Biometria biometria = request.toModel(cartao);
		em.persist(biometria);
		URI uri = uriComponentsBuilder.path("/biometria/{id}").build(biometria.getId());
		return ResponseEntity.created(uri).build();
	}
	
}
