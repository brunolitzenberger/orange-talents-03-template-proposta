package br.com.zupacademy.bruno.proposta.manager;

import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class GerenciadorDeTransacao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public <T> T salvaEComita(T objeto) {
		em.persist(objeto);
		return objeto;
	}

	@Transactional
	public <T> T atualizaEComita(T objeto) {
		return em.merge(objeto);
	}

	@Transactional
	public <T> T executa(Supplier<T> algumCodigoComRetorno) {
		return algumCodigoComRetorno.get();
	}
}
