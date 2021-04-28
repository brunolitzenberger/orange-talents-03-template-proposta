package br.com.zupacademy.bruno.proposta.controller.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.proposta.controller.enums.CarteiraDigital;

@Entity
public class Carteira {

	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private CarteiraDigital carteira;

	private @Email @NotBlank String email;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Cartao cartao;

	private @NotBlank String legadoId;

	@Deprecated
	public Carteira() {

	}

	public CarteiraDigital getCarteira() {
		return carteira;
	}

	public Carteira(@Email @NotBlank String email, @NotBlank CarteiraDigital carteira, String legadoId, Cartao cartao) {
		this.email = email;
		this.carteira = carteira;
		this.legadoId = legadoId;
		this.cartao = cartao;
	}

	public UUID getId() {
		return id;
	}

}
