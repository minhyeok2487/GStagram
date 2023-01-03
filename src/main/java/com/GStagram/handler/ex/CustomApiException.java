package com.GStagram.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{


	public CustomApiException(String message, Map<String, String> errorMap) {
		super(message);
	}

	public CustomApiException(String message) {
		super(message);
	}

}
