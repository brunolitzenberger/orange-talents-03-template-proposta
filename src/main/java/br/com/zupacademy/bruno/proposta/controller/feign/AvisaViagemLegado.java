package br.com.zupacademy.bruno.proposta.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.bruno.proposta.controller.request.RequestAvisoDeViagem;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseAvisoLegado;

@FeignClient(value = "aviso", url = "${proposta.cartao.url}")
public interface AvisaViagemLegado {
	
	@PostMapping("/api/cartoes/{id}/avisos")
	ResponseAvisoLegado avisaViagem(@PathVariable String id, RequestAvisoDeViagem request);

}
