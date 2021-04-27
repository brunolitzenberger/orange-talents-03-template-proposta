package br.com.zupacademy.bruno.proposta.manager;

import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
	public <T> Object encontra(Class<?> objeto, T  id) {
		Object obj = em.find(objeto, id);
		if(obj == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado");
		}
		return obj;
	}
	
	
	@Transactional
	public <T> T executa(Supplier<T> algumCodigoComRetorno) {
		return algumCodigoComRetorno.get();
	}
}
