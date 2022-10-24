package com.GStagram.domain.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
	public String signup() {
		System.out.println("signup이 실행됨");
		return "auth/login"; // 회원가입이 완료되면 로그인 페이지로 이동한다.
	}
}
