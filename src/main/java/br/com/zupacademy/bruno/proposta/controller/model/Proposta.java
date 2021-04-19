package br.com.zupacademy.bruno.proposta.controller.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.bruno.proposta.controller.enums.AvaliacaoFinanceira;
import br.com.zupacademy.bruno.proposta.controller.request.RequestCartao;
import br.com.zupacademy.bruno.proposta.controller.validators.UniqueValue;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String documento;
	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String endereco;
	private @Positive @NotNull BigDecimal salario;
	@Enumerated(EnumType.STRING)
	private AvaliacaoFinanceira avaliacaoFinanceira;
	
	@Deprecated
	public Proposta	() {
		
	}

	public Proposta(@NotBlank String cpfOuCnpj, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		this.documento = cpfOuCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}
	
	public void adicionaAvaliacaoFinanceira(AvaliacaoFinanceira avaliacao) {
		this.avaliacaoFinanceira = avaliacao;
	}
	
	public RequestCartao toRequestCartao() {
		return new RequestCartao(documento, nome, id.toString());
	}

}
