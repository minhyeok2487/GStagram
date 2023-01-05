package com.GStagram.web.api;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.user.User;
import com.GStagram.handler.ex.CustomValidationApiException;
import com.GStagram.handler.ex.CustomValidationException;
import com.GStagram.service.UserService;
import com.GStagram.web.dto.CMRespDto;
import com.GStagram.web.dto.user.UserUpdateDto;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
		@PathVariable int id,
		@Valid UserUpdateDto userUpdateDto,
		BindingResult bindingResult, // 꼭 @Valid가 적혀있는 파라메터 다음 적어야함
		@AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationApiException("유효성 검사 실패",errorMap);
		} else {
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			principalDetails.setUser(userEntity); // 세션정보 변경
			return new CMRespDto<>(1, "회원정보 수정 완료", userEntity);
			// 응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
		}
	}
}
