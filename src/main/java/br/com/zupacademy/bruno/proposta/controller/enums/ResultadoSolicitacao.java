package br.com.zupacademy.bruno.proposta.controller.enums;

public enum ResultadoSolicitacao {
	SEM_RESTRICAO,
	COM_RESTRICAO;
	
	public ElegibilidadeParaCartao converter() {
		if(this.equals(SEM_RESTRICAO)) {
			return ElegibilidadeParaCartao.ELEGIVEL;
		}
		return ElegibilidadeParaCartao.NAO_ELEGIVEL;
	}
	
	
}
