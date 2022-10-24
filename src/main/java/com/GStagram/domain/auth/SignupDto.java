package com.GStagram.domain.auth;

import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	private String username;
	private String password;
	private String email;
	private String name;
}
