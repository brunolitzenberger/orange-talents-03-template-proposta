package br.com.zupacademy.bruno.proposta.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.proposta.controller.model.Bloqueio;
import br.com.zupacademy.bruno.proposta.controller.model.Cartao;

public class RequestBloqueio {

	@NotBlank
	private String sistemaResponsavel;

	public RequestBloqueio(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
	public Bloqueio toModel(String ip, Cartao cartao) {
		return new Bloqueio(sistemaResponsavel, ip, cartao);
	}
	
	
	
}
