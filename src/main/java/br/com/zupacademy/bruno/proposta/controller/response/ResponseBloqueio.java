package br.com.zupacademy.bruno.proposta.controller.response;

import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoBloqueio;
import br.com.zupacademy.bruno.proposta.controller.model.Bloqueio;

public class ResponseBloqueio {
	
	private ResultadoBloqueio resultado;
	
	public void setResultado(ResultadoBloqueio resultado) {
		this.resultado = resultado;
	}

	public ResultadoBloqueio getResultado() {
		return resultado;
	}

	
	
}
