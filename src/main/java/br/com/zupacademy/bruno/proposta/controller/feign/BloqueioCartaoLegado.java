package br.com.zupacademy.bruno.proposta.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.bruno.proposta.controller.request.RequestBloqueio;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseBloqueio;

@FeignClient(name = "bloqueio", url = "${proposta.cartao.url}")
public interface BloqueioCartaoLegado {

	@PostMapping("/api/cartoes/{id}/bloqueios")
	ResponseBloqueio solicitacaoDeBloqueio(@PathVariable String id, RequestBloqueio request);
	
}
