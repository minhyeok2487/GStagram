package com.GStagram.service;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.subscribe.SubscribeRepository;
import com.GStagram.domain.user.User;
import com.GStagram.domain.user.UserRepository;
import com.GStagram.handler.ex.CustomException;
import com.GStagram.handler.ex.CustomValidationApiException;
import com.GStagram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final SubscribeRepository subscribeRepository;

	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto dto = new UserProfileDto();
		// SELECT * FROM image WHERE userId = :userId; -> JPA
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == principalId);
		dto.setImageCount(userEntity.getImages().size());
		int subscribeState = subscribeRepository.mSubscribeState(principalId,pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		dto.setSubscribeState(subscribeState == 1);
		dto.setSubscribeCount(subscribeCount);
		userEntity.getImages().forEach((image -> {
			image.setLikeCount(image.getLikes().size());
		}));
		return dto;
	}

	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new CustomValidationApiException("찾을 수 없는 id입니다.");
		});
		// 1) 무조건 찾았다. 걱정마 get() 2) 못찾았어 익셉션 발동시킬께 orElseThrow()

		// 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
		userEntity.setName(user.getName());
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		if(!user.getBio().equals("")) {
			userEntity.setBio(user.getBio());
		}
		if(!user.getWebsite().equals("")) {
			userEntity.setWebsite(user.getWebsite());
		}
		if(!user.getPhone().equals("")) {
			userEntity.setPhone(user.getPhone());
		}
		if(!user.getGender().equals("")) {
			userEntity.setGender(user.getGender());
		}
		return userEntity;
		// 더티체킹이 일어나서 업데이트가 완료됨
	}
}
