package br.com.zupacademy.bruno.proposta.controller.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoElegibilidade;
import br.com.zupacademy.bruno.proposta.controller.request.RequestCartao;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseProposta;

@Entity
public class Proposta {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	private @NotBlank String documento;
	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String endereco;
	private @Positive @NotNull BigDecimal salario;
	@Enumerated(EnumType.STRING)
	private ResultadoElegibilidade elegibilidade;

	@OneToOne(cascade = CascadeType.MERGE, mappedBy = "proposta")
	private Cartao cartao;

	@Deprecated
	public Proposta() {

	}

	public Proposta(@NotBlank String cpfOuCnpj, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		this.documento = cpfOuCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public UUID getId() {
		return id;
	}

	public void adicionaElegibilidade(ResultadoElegibilidade elegibilidade) {
		this.elegibilidade = elegibilidade;
	}

	public RequestCartao toRequestCartao() {
		return new RequestCartao(documento, nome, id.toString());
	}

	public void adicionaCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public boolean temCartao() {
		return this.cartao != null;
	}

	public ResponseProposta toResponse() {
		return new ResponseProposta(nome, elegibilidade, cartao.dataEmissaoCartao());
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + ", elegibilidade=" + elegibilidade + ", cartao=" + cartao + "]";
	}
	
	
}
