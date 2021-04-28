package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue
	private UUID id;
	private @NotBlank String destino;
	private @NotBlank String userAgent;
	private @NotBlank String ipDoCliente;
	private @Future LocalDate termino;
	private LocalDateTime instante = LocalDateTime.now();
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
		
	}
	
	public AvisoViagem(@NotBlank String destino, @NotBlank String userAgent, @Future LocalDate termino, Cartao cartao, String ip) {
		this.destino = destino;
		this.userAgent = userAgent;
		this.termino = termino;
		this.cartao = cartao;
		this.ipDoCliente = ip;
	}

}
