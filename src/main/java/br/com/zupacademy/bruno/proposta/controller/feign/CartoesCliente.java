package br.com.zupacademy.bruno.proposta.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.bruno.proposta.controller.request.RequestCartao;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseCartao;

@FeignClient(value = "cartoes", url = "${proposta.analise.url}")
public interface CartoesCliente {

    @PostMapping("api/solicitacao")
    ResponseCartao elegibilidadeCartao(RequestCartao request);
}
