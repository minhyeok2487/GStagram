package com.GStagram.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

	private Map<String, String> errorMap;

	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}

	public CustomValidationApiException(String message) {
		super(message);
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}
}
