package br.com.zupacademy.bruno.proposta.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import br.com.zupacademy.bruno.proposta.controller.feign.CartoesCliente;
import br.com.zupacademy.bruno.proposta.controller.model.Proposta;
import br.com.zupacademy.bruno.proposta.controller.request.NovaPropostaRequest;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseCartao;
import br.com.zupacademy.bruno.proposta.manager.GerenciadorDeTransacao;
import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {

	private GerenciadorDeTransacao gerenciadorDeTransacao;
	private CartoesCliente cliente;
	@PersistenceContext
	private EntityManager em;
	
	public NovaPropostaController(GerenciadorDeTransacao gerenciadorDeTransacao, CartoesCliente cliente) {
		this.gerenciadorDeTransacao = gerenciadorDeTransacao;
		this.cliente = cliente;
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder){
		Proposta novaProposta = request.toModel();
		gerenciadorDeTransacao.salvaEComita(novaProposta);
		solicitarCartao(novaProposta);
		gerenciadorDeTransacao.atualizaEComita(novaProposta);
		URI uri = builder.path("/propostas/{id}").build(novaProposta.getId());
		return ResponseEntity.created(uri).build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> acompanhar(@PathVariable Long id){	
		Proposta proposta = em.find(Proposta.class, id);
		if(proposta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(proposta.toResponse());
	}
	
	public void solicitarCartao(Proposta novaProposta) {
		try {
			ResponseCartao elegibilidadeParaCartao = cliente.elegibilidadeCartao(novaProposta.toRequestCartao());
			novaProposta.adicionaElegibilidade(elegibilidadeParaCartao.toElegibilidade());
		} catch (FeignException.UnprocessableEntity e) {
			ResponseCartao solicitacaoDeCartao = new Gson().fromJson(e.contentUTF8(), ResponseCartao.class);
			novaProposta.adicionaElegibilidade(solicitacaoDeCartao.toElegibilidade());
		}		
	}
}

