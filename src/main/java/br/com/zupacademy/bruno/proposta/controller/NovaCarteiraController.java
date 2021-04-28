package br.com.zupacademy.bruno.proposta.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.bruno.proposta.controller.feign.CarteiraDigitalLegado;
import br.com.zupacademy.bruno.proposta.controller.model.Cartao;
import br.com.zupacademy.bruno.proposta.controller.model.Carteira;
import br.com.zupacademy.bruno.proposta.controller.request.RequestCarteira;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseCarteiraLegado;
import br.com.zupacademy.bruno.proposta.manager.GerenciadorDeTransacao;
import feign.FeignException;

@RestController
@RequestMapping
public class NovaCarteiraController {

	private GerenciadorDeTransacao gerenciadorDeTransacao;

	private CarteiraDigitalLegado carteiraLegado;

	public NovaCarteiraController(GerenciadorDeTransacao gerenciadorDeTransacao, CarteiraDigitalLegado carteiraLegado) {
		this.gerenciadorDeTransacao = gerenciadorDeTransacao;
		this.carteiraLegado = carteiraLegado;
	}

	@PostMapping("/carteiras/{id}")
	public ResponseEntity<?> novaCarteira(@PathVariable String id, @RequestBody RequestCarteira request,
			UriComponentsBuilder builder) {
		Cartao cartao = (Cartao) gerenciadorDeTransacao.encontra(Cartao.class, id);
		return legadoCarteira(cartao, request, builder);
	}

	public ResponseEntity<?> legadoCarteira(Cartao cartao, RequestCarteira request, UriComponentsBuilder builder) {
		try {
			ResponseCarteiraLegado legado = carteiraLegado.adicionar(cartao.getId(), request);
			Carteira carteira = request.toModel(cartao, legado.getId());
			cartao.adicionaCarteira(carteira);
			gerenciadorDeTransacao.salvaEComita(carteira);
			URI uri = builder.path("/carteiras/{id}").build(carteira.getId());
			return ResponseEntity.created(uri).build();
		} catch (FeignException e) {
			return ResponseEntity.unprocessableEntity().body("Não foi possível associar a carteira.");
		}
		
	}

}
