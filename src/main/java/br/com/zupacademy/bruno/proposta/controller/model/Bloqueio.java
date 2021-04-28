package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Bloqueio {


	@Id
	@GeneratedValue
	private UUID id;
	@NotBlank 
	private String userAgent;
	@NotBlank
	private String ip;
	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now(); 
	@OneToOne
	private Cartao cartao;
	
	
	@Deprecated
	public Bloqueio() {
		
	}
	
	public Bloqueio(@NotBlank String userAgent, String ip, Cartao cartao) {
		this.userAgent = userAgent;
		this.ip = ip;
		this.cartao = cartao;
	}
	
	

}
