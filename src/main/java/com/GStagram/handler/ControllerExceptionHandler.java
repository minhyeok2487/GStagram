package com.GStagram.handler;

import com.GStagram.handler.ex.CustomValidationException;
import com.GStagram.util.Script;
import com.GStagram.web.dto.CMRespDto;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

//	@ExceptionHandler(CustomValidationException.class)
//	public CMRespDto<?> validationException(CustomValidationException e) {
//		return new CMRespDto(-1,e.getMessage(),e.getErrorMap());
//	}

	@ExceptionHandler(CustomValidationException.class)
	public String  validationException(CustomValidationException e) {
		// CMRespDto, Script 비교
		// 1. 클라이언트에게 응답할 때는 Script 좋음
		// 2. Ajax통신 - CMRespDto
		// 3. Android 통신 - CMRespDto
		return Script.back(e.getErrorMap().toString());
	}
}
