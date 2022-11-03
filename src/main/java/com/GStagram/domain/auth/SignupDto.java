package com.GStagram.domain.auth;

import com.GStagram.model.User;
import javax.validation.constraints.Size;
import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	@Size(min=3, max=30)
	private String username;

	@Size(min=1, max=20)
	private String password;

	@Size(min=5, max=40)
	private String email;

	@Size(min=1, max=20)
	private String name;

	public User toEntity() {
		return User.builder()
			.username(username)
			.password(password)
			.email(email)
			.name(name)
			.build();
	}
}
