package br.com.zupacademy.bruno.proposta.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
