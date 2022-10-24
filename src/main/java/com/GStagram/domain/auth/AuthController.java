package com.GStagram.domain.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AuthController {

	@GetMapping("/auth/login")
	public String loginPage() {
		return "auth/login";
	}

	@GetMapping("/auth/signup")
	public String signupPage() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // key = value (x-www-form-urlencoded)
		log.info(signupDto.toString());
		return "auth/login"; // 회원가입이 완료되면 로그인 페이지로 이동한다.
	}
}
