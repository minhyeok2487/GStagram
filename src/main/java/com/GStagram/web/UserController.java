package com.GStagram.web;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.user.User;
import com.GStagram.service.UserService;
import com.GStagram.web.dto.user.UserProfileDto;
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

	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable Long pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto userEntity = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("user", userEntity.getUser());
		model.addAttribute("website", userEntity.getUser().getWebsite());
		model.addAttribute("bio", userEntity.getUser().getBio());
		model.addAttribute("gender", userEntity.getUser().getGender());
		model.addAttribute("phone", userEntity.getUser().getPhone());
		model.addAttribute("imageSize", userEntity.getImageCount());
		return "user/profile";
	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		model.addAttribute("principalDetails", principalDetails.getUser());
		return "user/update";
	}

}
