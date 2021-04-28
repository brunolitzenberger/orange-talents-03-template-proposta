package br.com.zupacademy.bruno.proposta.controller.response;

import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoCarteira;

public class ResponseCarteiraLegado {

	private ResultadoCarteira resultado;
	private String id;

	public ResponseCarteiraLegado(ResultadoCarteira resultado, String id) {
		super();
		this.resultado = resultado;
		this.id = id;
	}

	public ResultadoCarteira getResultado() {
		return resultado;
	}

	public String getId() {
		return id;
	}	
	
}
