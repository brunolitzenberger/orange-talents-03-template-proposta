package br.com.zupacademy.bruno.proposta.controller.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
