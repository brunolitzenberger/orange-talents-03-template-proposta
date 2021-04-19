package br.com.zupacademy.bruno.proposta.controller.enums;

public enum ResultadoSolicitacao {
	SEM_RESTRICAO,
	COM_RESTRICAO;
	
	public AvaliacaoFinanceira converter() {
		if(this.equals(SEM_RESTRICAO)) {
			return AvaliacaoFinanceira.ELEGIVEL;
		}
		return AvaliacaoFinanceira.NAO_ELEGIVEL;
	}
	
	
}
