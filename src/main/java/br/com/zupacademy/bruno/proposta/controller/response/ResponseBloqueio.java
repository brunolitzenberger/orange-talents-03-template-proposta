package br.com.zupacademy.bruno.proposta.controller.response;

import br.com.zupacademy.bruno.proposta.controller.enums.Bloqueios;
import br.com.zupacademy.bruno.proposta.controller.model.Bloqueio;

public class ResponseBloqueio {
	
	private Bloqueios resultado;
	
	public void setResultado(Bloqueios resultado) {
		this.resultado = resultado;
	}

	public Bloqueios getResultado() {
		return resultado;
	}

	
	
}
