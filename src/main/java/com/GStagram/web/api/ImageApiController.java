package com.GStagram.web.api;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.Image.Image;
import com.GStagram.domain.user.User;
import com.GStagram.handler.ex.CustomValidationApiException;
import com.GStagram.service.ImageService;
import com.GStagram.service.SubscribeService;
import com.GStagram.service.UserService;
import com.GStagram.web.dto.CMRespDto;
import com.GStagram.web.dto.subscribe.SubscribeDto;
import com.GStagram.web.dto.user.UserUpdateDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageApiController {

	private final ImageService imageService;

	@GetMapping("/api/image")
	public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
		@PageableDefault(size=3, sort="id") Pageable pageable) {
		Page<Image> images = imageService.imageStory(principalDetails.getUser().getId(), pageable);
		return new ResponseEntity<>(new CMRespDto<>(1, "성공", images),HttpStatus.OK);
	}
}
