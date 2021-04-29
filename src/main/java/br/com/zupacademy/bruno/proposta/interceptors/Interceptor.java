package br.com.zupacademy.bruno.proposta.interceptors;

import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
@Component
public class Interceptor implements RequestInterceptor{
    private final Tracer tracer;

    public Interceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void apply(RequestTemplate template) {
        tracer.inject(tracer.activeSpan().context(), Format.Builtin.HTTP_HEADERS, new FeignTextMap(template));
    }
}
