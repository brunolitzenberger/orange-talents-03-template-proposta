package br.com.zupacademy.bruno.proposta.schedule;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class AgendaCartao {
	
	@Autowired
	private VerificarCartao verificarCartao;
	
	@Scheduled(fixedDelay = 200000)
	public void verifica() {
		verificarCartao.verificar();
	}

}
