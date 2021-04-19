package br.com.zupacademy.bruno.proposta.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping
public class NovaPropostaController {

	@Autowired
	private GerenciadorDeTransacao transactionManager;
	
	@Autowired
	private CartoesCliente cliente;
	
	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder){
		Proposta novaProposta = request.toModel();
		transactionManager.salvaEComita(novaProposta);
		solicitarCartao(novaProposta);
		transactionManager.atualizaEComita(novaProposta);
		URI uri = builder.path("/propostas/{id}").build(novaProposta.getId());
		return ResponseEntity.created(uri).build();
	}
	
	public void solicitarCartao(Proposta novaProposta) {
		try {
			ResponseCartao solicitacaoDeCartao = cliente.solicitacaoDeCartao(novaProposta.toRequestCartao());	
			novaProposta.adicionaAvaliacaoFinanceira(solicitacaoDeCartao.toAvaliacao());
		} catch (FeignException.UnprocessableEntity e) {
			ResponseCartao solicitacaoDeCartao = new Gson().fromJson(e.contentUTF8(), ResponseCartao.class);
			novaProposta.adicionaAvaliacaoFinanceira(solicitacaoDeCartao.toAvaliacao());
		}		
	}
	
}

