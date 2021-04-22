package br.com.zupacademy.bruno.proposta.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.zupacademy.bruno.proposta.controller.response.ResponseSolicitacaoCartao;

@FeignClient(value = "solicitacoes", url = "${proposta.cartao.url}")
public interface SolicitacaoDeCartao {
	@GetMapping("api/cartoes?idProposta={id}")
	ResponseSolicitacaoCartao statusSolicitacaoDeCartao(@PathVariable String id);
}
