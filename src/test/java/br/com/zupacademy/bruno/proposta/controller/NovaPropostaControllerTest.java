package br.com.zupacademy.bruno.proposta.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupacademy.bruno.proposta.controller.request.NovaPropostaRequest;

@IfProfileValue(name ="spring.profiles.active", value = "dev")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class NovaPropostaControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	ObjectMapper jsonMapper;
	/*
	@Test
	public void deveriaValidarCpf() throws Exception {

		mock.perform(post("/propostas")
		.content(this.json(new NovaPropostaRequest("09557981911", "bruno@zup.com.br", "Bruno", "dokasodkasodk", BigDecimal.TEN)))
		.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(header().string(LOCATION, endsWith("/propostas/1")));
		
	}

	private String json(NovaPropostaRequest request) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(request);
	}
*/
}
