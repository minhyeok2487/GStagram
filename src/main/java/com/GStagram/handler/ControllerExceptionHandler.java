package com.GStagram.handler;

import com.GStagram.handler.ex.CustomValidationException;
import com.GStagram.web.dto.CMRespDto;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
		return new CMRespDto(-1,e.getMessage(),e.getErrorMap());
	}
}
