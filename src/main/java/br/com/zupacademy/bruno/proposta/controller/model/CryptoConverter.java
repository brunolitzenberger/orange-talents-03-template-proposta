package br.com.zupacademy.bruno.proposta.controller.model;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.crypto.encrypt.Encryptors;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {
	
	
    @SuppressWarnings("deprecation")
	@Override
    public String convertToDatabaseColumn(String documento) {
      try {
    	 return Encryptors.queryableText("${proposta.ofuscamento.texto}", "0ed3febbb5edc10a").encrypt(documento);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
    }
 
    @SuppressWarnings("deprecation")
	@Override
    public String convertToEntityAttribute(String documento) {
      try {
    	return Encryptors.queryableText("${proposta.ofuscamento.texto}", "0ed3febbb5edc10a").decrypt(documento);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
}
