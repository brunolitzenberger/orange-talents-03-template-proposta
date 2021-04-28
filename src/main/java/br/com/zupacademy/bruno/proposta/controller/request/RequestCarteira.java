package br.com.zupacademy.bruno.proposta.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.bruno.proposta.controller.enums.CarteiraDigital;
import br.com.zupacademy.bruno.proposta.controller.model.Cartao;
import br.com.zupacademy.bruno.proposta.controller.model.Carteira;

public class RequestCarteira {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	private CarteiraDigital carteira;

	public RequestCarteira(@Email @NotBlank String email, @NotBlank CarteiraDigital carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public CarteiraDigital getCarteira() {
		return carteira;
	}
	
	public Carteira toModel(Cartao cartao, String legadoId) {		
		boolean validaCarteira = cartao.validaCarteira(carteira);
		if(validaCarteira) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já associada.");
		}
		return new Carteira(email, carteira, legadoId, cartao);
	}

	@Override
	public String toString() {
		return "RequestCateira [email=" + email + ", carteira=" + carteira + "]";
	}

}
