package com.GStagram.config.auth;

import com.GStagram.domain.user.User;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

@Data
public class PrincipalDetails implements UserDetails {

	private User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 권한 : 한개가 아닐 수 있음. (3개 이상의 권한)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
