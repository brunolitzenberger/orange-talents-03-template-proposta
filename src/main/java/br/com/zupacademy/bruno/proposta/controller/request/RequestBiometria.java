package br.com.zupacademy.bruno.proposta.controller.request;

import br.com.zupacademy.bruno.proposta.controller.model.Biometria;
import br.com.zupacademy.bruno.proposta.controller.model.Cartao;

public class RequestBiometria {

	private String biometria;

	public void setBiometria(String biometria) {
		this.biometria = biometria;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(biometria, cartao);
	}

}
