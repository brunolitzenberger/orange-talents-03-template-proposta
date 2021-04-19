package br.com.zupacademy.bruno.proposta.controller.response;

import java.time.LocalDateTime;

import br.com.zupacademy.bruno.proposta.controller.enums.ElegibilidadeParaCartao;

public class ResponseProposta {

	private String nome;
	private ElegibilidadeParaCartao elegibilidade;
	private LocalDateTime emitidoEm;

	public ResponseProposta(String nome, ElegibilidadeParaCartao elegibilidade, LocalDateTime emitidoEm) {
		super();
		this.nome = nome;
		this.elegibilidade = elegibilidade;
		this.emitidoEm = emitidoEm;
	}

	public String getNome() {
		return nome;
	}

	public ElegibilidadeParaCartao getElegibilidade() {
		return elegibilidade;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	
	

}
