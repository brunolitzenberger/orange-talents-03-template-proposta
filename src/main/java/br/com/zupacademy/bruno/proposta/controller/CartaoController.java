package br.com.zupacademy.bruno.proposta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.proposta.controller.model.Cartao;
import br.com.zupacademy.bruno.proposta.controller.request.RequestBloqueio;
import br.com.zupacademy.bruno.proposta.manager.GerenciadorDeTransacao;

@RestController
@RequestMapping
public class CartaoController {

	private GerenciadorDeTransacao gerenciadorDeTransacao;

	private HttpServletRequest httpRequest;


	public CartaoController(GerenciadorDeTransacao gerenciadorDeTransacao, HttpServletRequest httpRequest
			) {
		this.httpRequest = httpRequest;
		this.gerenciadorDeTransacao = gerenciadorDeTransacao;
	}

	@PostMapping("/{id}/bloquear")
	public ResponseEntity<?> bloquearCartao(@PathVariable String id, @RequestBody @Valid RequestBloqueio request) {
		Cartao cartao = (Cartao) gerenciadorDeTransacao.encontra(Cartao.class, id);
		if(cartao.temBloqueio()) {
			return ResponseEntity.unprocessableEntity().body("Cartão já bloqueado.");
		}
		cartao.adicionaBloqueio(request.toModel(httpRequest.getLocalAddr(), cartao));
		gerenciadorDeTransacao.atualizaEComita(cartao);
		return ResponseEntity.ok().body("Bloqueado com sucesso.");
	}

}
