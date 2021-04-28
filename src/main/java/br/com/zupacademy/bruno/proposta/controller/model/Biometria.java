package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Biometria {

	@Id
	@GeneratedValue
	private UUID id;

	@NotBlank
	private String digital;

	@PastOrPresent
	private LocalDate dataAssociacao = LocalDate.now();

	@ManyToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	@Deprecated
	public Biometria() {

	}

	public Biometria(String biometria, Cartao cartao) {
		super();
		this.digital = encodeBiometria(biometria);
		this.cartao = cartao;
	}

	private String encodeBiometria(String digital) {
		return Base64.getEncoder().encodeToString(digital.getBytes());
	}

	public UUID getId() {
		return this.id;
	}

}
