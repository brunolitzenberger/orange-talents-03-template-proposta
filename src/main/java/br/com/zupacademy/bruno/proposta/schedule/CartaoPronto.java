package br.com.zupacademy.bruno.proposta.schedule;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.proposta.controller.enums.ElegibilidadeParaCartao;
import br.com.zupacademy.bruno.proposta.controller.feign.SolicitacaoDeCartao;
import br.com.zupacademy.bruno.proposta.controller.model.Proposta;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseSolicitacaoCartao;
import br.com.zupacademy.bruno.proposta.manager.GerenciadorDeTransacao;

@Component
public class CartaoPronto {

	@Autowired
	private EntityManager em;

	@Autowired
	private SolicitacaoDeCartao solicitacaoDeCartao;

	@Autowired
	private GerenciadorDeTransacao transactionManager;

	public void verificaCartao() {
		/**
		 * Faz uma busca por propostas que não tenham cartão e que tenha a avaliação
		 * financeira elegível
		 */
		List<Proposta> cartaoEmEspera = em
				.createQuery("SELECT p FROM Proposta p " 
						+ "LEFT JOIN p.cartao c " 
						+ "WHERE c IS NULL "
						+ "AND  p.elegibilidade = :elegibilidade", Proposta.class)
				.setParameter("elegibilidade", ElegibilidadeParaCartao.ELEGIVEL).getResultList();
		try {
			for (Proposta result : cartaoEmEspera) {
				ResponseSolicitacaoCartao statusSolicitacaoDeCartao = solicitacaoDeCartao
						.statusSolicitacaoDeCartao(result.getId().toString());
				result.adicionaCartao(statusSolicitacaoDeCartao.toCartao(result));
				transactionManager.atualizaEComita(result);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
