package br.com.zupacademy.bruno.proposta.controller.request;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.bruno.proposta.controller.model.Proposta;
import br.com.zupacademy.bruno.proposta.controller.validators.CpfOuCnpj;
import br.com.zupacademy.bruno.proposta.controller.validators.UniqueValue;

public class NovaPropostaRequest {

	@NotBlank
	@CpfOuCnpj
	@NotBlank @UniqueValue(domainClass = Proposta.class, fieldName = "documento", message = "Proposta já cadastrada")
	private String documento;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@Positive
	@NotNull
	private BigDecimal salario;

	public NovaPropostaRequest(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome, @NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}


	public Proposta toModel(){
		return new Proposta(documento, email, nome, endereco, salario);
	}
	

}
