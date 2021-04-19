package br.com.zupacademy.bruno.proposta.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class VerificaCartao {
	
	@Autowired
	private CartaoPronto gerarCartao;
	
	@Scheduled(fixedDelay = 2000)
	private void verifica() {
		gerarCartao.verificaCartao();
	}

}
