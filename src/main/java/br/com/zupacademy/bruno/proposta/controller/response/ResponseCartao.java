package br.com.zupacademy.bruno.proposta.controller.response;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.bruno.proposta.controller.enums.ElegibilidadeParaCartao;
import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoSolicitacao;

public class ResponseCartao {

	@NotBlank
	private String documento;
	@NotBlank
	private String idProposta;
	@NotBlank
	private String nome;
	private ResultadoSolicitacao resultadoSolicitacao;

	

	public ResponseCartao(@NotBlank String documento, @NotBlank String idProposta, @NotBlank String nome,
			ResultadoSolicitacao resultadoSolicitacao) {
		this.documento = documento;
		this.idProposta = idProposta;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public ElegibilidadeParaCartao toElegibilidade() {
		return resultadoSolicitacao.converter();
	}

	
	

}
