package br.com.zupacademy.bruno.proposta.metricas;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;

public class Metricas {

	@Component
	class MinhasMetricas {

		private final MeterRegistry meterRegistry;

		public MinhasMetricas(MeterRegistry meterRegistry) {
			this.meterRegistry = meterRegistry;
		}

	}	

}
