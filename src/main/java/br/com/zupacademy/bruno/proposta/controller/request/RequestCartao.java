package br.com.zupacademy.bruno.proposta.controller.request;

import javax.validation.constraints.NotBlank;

public class RequestCartao {

	@NotBlank
	private String documento;
	@NotBlank
	private String nome;
	@NotBlank
	private String idProposta;

	public RequestCartao(@NotBlank String documento, @NotBlank String nome, @NotBlank String idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
