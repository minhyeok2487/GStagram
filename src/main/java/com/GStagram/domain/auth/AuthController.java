package com.GStagram.domain.auth;

import com.GStagram.model.User;
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

@RequiredArgsConstructor
@Controller
@Slf4j
public class AuthController {

	private final AuthService authService;

	@GetMapping("/auth/login")
	public String loginPage() {
		return "auth/login";
	}

	@GetMapping("/auth/signup")
	public String signupPage() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key = value (x-www-form-urlencoded)
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("====================================");
				System.out.println(error.getDefaultMessage());
				System.out.println("====================================");
			}
		} else {
			log.info(signupDto.toString());
			User user = signupDto.toEntity();
			log.info(user.toString());
			User userEntity = authService.signupReg(user);
			log.info(userEntity.toString());
		}
		return "auth/login"; // 회원가입이 완료되면 로그인 페이지로 이동한다.
	}
}
