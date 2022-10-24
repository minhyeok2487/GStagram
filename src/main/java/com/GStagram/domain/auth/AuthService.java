package com.GStagram.domain.auth;

import com.GStagram.model.User;
import com.GStagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;

	//회원 가입 진행
	public User signupReg(User user) {
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
