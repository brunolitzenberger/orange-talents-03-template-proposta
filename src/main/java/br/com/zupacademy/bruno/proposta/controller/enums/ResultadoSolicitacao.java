package br.com.zupacademy.bruno.proposta.controller.enums;

public enum ResultadoSolicitacao {
	SEM_RESTRICAO,
	COM_RESTRICAO;
	
	public ResultadoElegibilidade converter() {
		if(this.equals(SEM_RESTRICAO)) {
			return ResultadoElegibilidade.ELEGIVEL;
		}
		return ResultadoElegibilidade.NAO_ELEGIVEL;
	}
	
	
}
