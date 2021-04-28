package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zupacademy.bruno.proposta.controller.enums.CarteiraDigital;
import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoBloqueio;
import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoParcela;
import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoRenegociacao;
import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoVencimento;

@Entity
public class Cartao {

	@Id
	private String id;

	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;

	@OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private Bloqueio bloqueio;

	@Enumerated(EnumType.STRING)
	private ResultadoBloqueio estadoDoCartao;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private Set<AvisoViagem> avisos = new HashSet<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private Set<Carteira> carteiras;
	@Enumerated(EnumType.STRING)
	private ResultadoParcela parcelas;
	@Positive
	private Integer limite;
	@Enumerated(EnumType.STRING)
	private ResultadoRenegociacao renegociacao;
	@Enumerated(EnumType.STRING)
	private ResultadoVencimento vencimento;

	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<>();

	@OneToOne()
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
		this.estadoDoCartao = ResultadoBloqueio.BLOQUEADO;
	}

	public void atualizaVencimento(ResultadoVencimento vencimento) {
		this.vencimento = vencimento;
	}

	public void adicionaAvisoViagem(AvisoViagem aviso) {
		this.avisos.add(aviso);
	}

	public void adicionaParcela(ResultadoParcela parcela) {
		this.parcelas = parcela;
	}

	public void adicionaRenegociacao(ResultadoRenegociacao renegociacao) {
		this.renegociacao = renegociacao;
	}

	public void adicionaCarteira(Carteira carteira) {
		this.carteiras.add(carteira);
	}

	public LocalDateTime dataEmissaoCartao() {
		return emitidoEm;
	}

	public boolean validaCarteira(CarteiraDigital carteira) {
		Set<Carteira> duplicado = this.carteiras.stream().filter(c -> carteira.equals(c.getCarteira()))
				.collect(Collectors.toSet());
		return duplicado.size() > 0;
		
	}
	
	

}
