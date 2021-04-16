package br.com.zupacademy.bruno.proposta.controller.request;

import br.com.zupacademy.bruno.proposta.controller.model.Proposta;
import br.com.zupacademy.bruno.proposta.controller.validators.CpfOuCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

	@NotBlank
	@CpfOuCnpj
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
