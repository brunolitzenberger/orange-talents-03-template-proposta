package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import com.sun.istack.NotNull;

import br.com.zupacademy.bruno.proposta.controller.enums.AvisoViagem;
import br.com.zupacademy.bruno.proposta.controller.enums.Bloqueios;
import br.com.zupacademy.bruno.proposta.controller.enums.Carteira;
import br.com.zupacademy.bruno.proposta.controller.enums.Parcela;
import br.com.zupacademy.bruno.proposta.controller.enums.Renegociacao;
import br.com.zupacademy.bruno.proposta.controller.enums.Vencimento;
import br.com.zupacademy.bruno.proposta.controller.request.RequestBiometria;

@Entity
public class Cartao {

	@Id
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	@Enumerated(EnumType.STRING)
	private Bloqueios bloqueios;
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

	public void adicionaBloqueio(Bloqueios bloqueios) {
		this.bloqueios = bloqueios;
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
