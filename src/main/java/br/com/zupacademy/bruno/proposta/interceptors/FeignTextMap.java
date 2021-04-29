package br.com.zupacademy.bruno.proposta.interceptors;

import java.util.Iterator;
import java.util.Map;

import feign.RequestTemplate;
import io.opentracing.propagation.TextMap;

public class FeignTextMap implements TextMap{
	
    private final RequestTemplate template;

    public FeignTextMap(RequestTemplate template) {
        this.template = template;
    }

    @Override
    public void put(String key, String value) {
        template.header(key, value);
    }

    /**
     * @Deprecated Para implementação do inject
     * @return Null, não implementado
     */
    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return null;
    }
}
