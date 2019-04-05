package com.desafio.cf.resources.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<CampoMensagem> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<CampoMensagem> getErrors() {
		return errors;
	}

	public void addError(String campo, String mensagem) {
		errors.add(new CampoMensagem(campo, mensagem));
	}
}
