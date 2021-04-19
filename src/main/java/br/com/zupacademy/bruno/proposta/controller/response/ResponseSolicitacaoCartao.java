package br.com.zupacademy.bruno.proposta.controller.response;

import java.time.LocalDateTime;
import java.util.List;

import br.com.zupacademy.bruno.proposta.controller.model.Cartao;
import br.com.zupacademy.bruno.proposta.controller.model.Proposta;
import br.com.zupacademy.bruno.proposta.controller.validators.UniqueValue;

public class ResponseSolicitacaoCartao {
	
	@UniqueValue(domainClass = Cartao.class, fieldName = "id")
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private List<?> bloqueios;
	private List<?> avisos;
	private List<?> carteiras;
	private List<?> parcelas;
	private Integer limite;
	private String renegociacao;
	private VencimentoResponse vencimento;
	private String idProposta;
	
	public ResponseSolicitacaoCartao(String id, LocalDateTime emitidoEm, String titular, List<?> bloqueios,
			List<?> avisos, List<?> carteiras, List<?> parcelas, Integer limite, String renegociacao,
			VencimentoResponse vencimento, String idReposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
		this.idProposta = idReposta;
	}
	public String getId() {
		return id;
	}
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	public String getTitular() {
		return titular;
	}
	public List<?> getBloqueios() {
		return bloqueios;
	}
	public List<?> getAvisos() {
		return avisos;
	}
	public List<?> getCarteiras() {
		return carteiras;
	}
	public List<?> getParcelas() {
		return parcelas;
	}
	public Integer getLimite() {
		return limite;
	}
	public String getRenegociacao() {
		return renegociacao;
	}
	public VencimentoResponse getVencimento() {
		return vencimento;
	}
	public String getIdProposta() {
		return idProposta;
	}
	
	public Cartao toCartao(Proposta proposta) {
		return new Cartao(id, emitidoEm, titular, limite, proposta);
	}
	
	
}
