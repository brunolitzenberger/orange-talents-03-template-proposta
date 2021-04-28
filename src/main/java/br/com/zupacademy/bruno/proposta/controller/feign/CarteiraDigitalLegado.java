package br.com.zupacademy.bruno.proposta.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.bruno.proposta.controller.request.RequestCarteira;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseCarteiraLegado;

@FeignClient(value = "carteira", url = "${proposta.cartao.url}")
public interface CarteiraDigitalLegado {

	@PostMapping("/api/cartoes/{id}/carteiras")
	ResponseCarteiraLegado adicionar(@PathVariable(required = true) String id, RequestCarteira carteira);
	
}
