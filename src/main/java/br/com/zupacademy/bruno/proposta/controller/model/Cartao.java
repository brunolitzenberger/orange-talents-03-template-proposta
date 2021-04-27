package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.sun.istack.NotNull;

import br.com.zupacademy.bruno.proposta.controller.enums.AvisoViagem;
import br.com.zupacademy.bruno.proposta.controller.enums.Carteira;
import br.com.zupacademy.bruno.proposta.controller.enums.Parcela;
import br.com.zupacademy.bruno.proposta.controller.enums.Renegociacao;
import br.com.zupacademy.bruno.proposta.controller.enums.Vencimento;

@Entity
public class Cartao {

	@Id
	private String id;
	@PastOrPresent
	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;

	@OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private Bloqueio bloqueio;
	@Enumerated(EnumType.STRING)
	private AvisoViagem avisos;
	@Enumerated(EnumType.STRING)
	private Carteira carteiras;
	@Enumerated(EnumType.STRING)
	private Parcela parcelas;
	private Integer limite;
	@Enumerated(EnumType.STRING)
	private Renegociacao renegociacao;
	@Enumerated(EnumType.STRING)
	private Vencimento vencimento;

	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<>();

	@OneToOne
	@NotNull
	private Proposta proposta;

	@Deprecated
	public Cartao() {

	}

	public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite, Proposta proposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public String getId() {
		return id;
	}
	
	public boolean temBloqueio() {
		return this.bloqueio != null;
	}

	public void adicionaBloqueio(Bloqueio bloqueio) {
		this.bloqueio = bloqueio;
	}

	public void atualizaVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}

	public void adicionaAvisoViagem(AvisoViagem aviso) {
		this.avisos = aviso;
	}

	public void adicionaParcela(Parcela parcela) {
		this.parcelas = parcela;
	}

	public void adicionaRenegociacao(Renegociacao renegociacao) {
		this.renegociacao = renegociacao;
	}

	public void adicionaCarteira(Carteira carteira) {
		this.carteiras = carteira;
	}

	public LocalDateTime dataEmissaoCartao() {
		return emitidoEm;
	}

}
