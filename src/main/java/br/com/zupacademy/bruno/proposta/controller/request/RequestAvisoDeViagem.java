package br.com.zupacademy.bruno.proposta.controller.request;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.proposta.controller.model.AvisoViagem;
import br.com.zupacademy.bruno.proposta.controller.model.Cartao;

public class RequestAvisoDeViagem {

	@NotBlank
	private String destino;
	@NotBlank
	private String userAgent;
	@Future
	private LocalDate termino;

	public RequestAvisoDeViagem(String destino, String userAgent, LocalDate termino) {
		super();
		this.destino = destino;
		this.userAgent = userAgent;
		this.termino = termino;
	}
	public AvisoViagem toModel(Cartao cartao, String ip) {
		return new AvisoViagem(destino, userAgent, termino, cartao, ip);
	}

	public String getDestino() {
		return destino;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public LocalDate getTermino() {
		return termino;
	}

	
	
}
