package com.GStagram.service;

import com.GStagram.domain.user.User;
import com.GStagram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 1.IoC 2.트랜잭션 관리
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	public User signupReg(User user) {
		// 회원가입 진행
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
