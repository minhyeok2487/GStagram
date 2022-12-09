package com.GStagram.web;

import com.GStagram.domain.user.User;
import com.GStagram.web.dto.auth.SignupDto;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.GStagram.service.AuthService;

@Slf4j
@Controller // 1.IoC 2.파일을 리턴하는 컨트롤러
@RequiredArgsConstructor // final 필드를 DI 할때 사용
public class AuthController {

	private final AuthService authService;
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
		}
		log.info(signupDto.toString());
		User user = signupDto.toEntity();
		log.info(user.toString());
		User userEntity = authService.signupReg(user);
		log.info(userEntity.toString());
		return "auth/signin";
	}
}
