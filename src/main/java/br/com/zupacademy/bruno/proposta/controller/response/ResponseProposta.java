package br.com.zupacademy.bruno.proposta.controller.response;

import java.time.LocalDateTime;

import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoElegibilidade;

public class ResponseProposta {

	private String nome;
	private ResultadoElegibilidade elegibilidade;
	private LocalDateTime emitidoEm;

	public ResponseProposta(String nome, ResultadoElegibilidade elegibilidade, LocalDateTime emitidoEm) {
		super();
		this.nome = nome;
		this.elegibilidade = elegibilidade;
		this.emitidoEm = emitidoEm;
	}

	public String getNome() {
		return nome;
	}

	public ResultadoElegibilidade getElegibilidade() {
		return elegibilidade;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	
	

}
