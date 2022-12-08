package com.GStagram.service;

import com.GStagram.domain.user.User;
import com.GStagram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 1.IoC 2.트랜잭션 관리
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional // Write(Insert, Update, Delete)
	public User signupReg(User user) {
		// 비밀번호 암호화
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		// 회원가입 진행
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
