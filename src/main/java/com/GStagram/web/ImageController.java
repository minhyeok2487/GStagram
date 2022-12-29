package com.GStagram.web;

import com.GStagram.domain.user.User;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ImageController {

	@GetMapping({"/","/image/story"})
	public String story() {
		return "image/story";
	}

	@GetMapping("/image/popular")
	public String popular() {
		return "image/popular";
	}

	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
}
