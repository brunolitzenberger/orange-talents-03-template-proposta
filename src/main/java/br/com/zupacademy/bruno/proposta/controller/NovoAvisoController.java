package br.com.zupacademy.bruno.proposta.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.proposta.controller.model.Cartao;
import br.com.zupacademy.bruno.proposta.controller.request.RequestAvisoDeViagem;

@RestController
@RequestMapping
public class NovoAvisoController {

	@PersistenceContext
	private EntityManager em;

	private HttpServletRequest httpRequest;

	public NovoAvisoController(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	@PostMapping("/avisos/{id}")
	@Transactional
	public ResponseEntity<?> novoAviso(@PathVariable(required = true) String id, @RequestBody @Valid RequestAvisoDeViagem request) {
		Cartao cartao = em.find(Cartao.class, id);
		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}
		cartao.adicionaAvisoViagem(request.toModel(cartao, httpRequest.getLocalAddr()));
		em.merge(cartao);
		return ResponseEntity.ok().build();
	}

}
