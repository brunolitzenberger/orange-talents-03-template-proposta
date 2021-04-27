package br.com.zupacademy.bruno.proposta.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupacademy.bruno.proposta.controller.enums.ResultadoSolicitacao;
import br.com.zupacademy.bruno.proposta.controller.feign.CartoesCliente;
import br.com.zupacademy.bruno.proposta.controller.feign.SolicitacaoDeCartao;
import br.com.zupacademy.bruno.proposta.controller.request.NovaPropostaRequest;
import br.com.zupacademy.bruno.proposta.controller.request.RequestCartao;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseCartao;
import br.com.zupacademy.bruno.proposta.controller.response.ResponseSolicitacaoCartao;
import br.com.zupacademy.bruno.proposta.controller.response.VencimentoResponse;

@IfProfileValue(name ="spring.profiles.active", value = "dev")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class NovaPropostaControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	ObjectMapper jsonMapper;
	
	@MockBean
	private CartoesCliente cartoesCliente;
	@MockBean
	private SolicitacaoDeCartao solicitacaoDeCartao;
	

	@Test
	public void deveriaCriarProposta() throws Exception {


		mock.perform(post("/propostas")
		.content(this.json(new NovaPropostaRequest("31995813079", "bruno@zup.com.br", "Bruno", "dokasodkasodk", BigDecimal.TEN)))
		.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
		
	}

	private String json(NovaPropostaRequest request) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(request);
	}

}
