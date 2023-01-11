package com.GStagram.web;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.user.User;
import com.GStagram.service.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/user/{id}")
	public String profile(@PathVariable Long id, Model model) {
		User userEntity = userService.회원프로필(id);
		model.addAttribute("user", userEntity);
		model.addAttribute("website", userEntity.getWebsite());
		model.addAttribute("bio", userEntity.getBio());
		model.addAttribute("gender", userEntity.getGender());
		model.addAttribute("phone", userEntity.getPhone());
		return "user/profile";
	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		model.addAttribute("principalDetails", principalDetails.getUser());
		return "user/update";
	}

}
