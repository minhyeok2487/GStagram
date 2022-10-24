package com.GStagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
		// 인증이 되지 않은 모든 사용자는 로그인 페이지로 이동
		http.csrf().disable();
		http
			.authorizeRequests() // URL별 관리
			.antMatchers("/auth/**", "/css/**","/images/**","/js/**","/h2-console/**").permitAll() //유저 관련 페이지만 접근 가능
			.anyRequest().authenticated() // 설정된 값들 이외 나머지 URL은 인증된 사용자만 접근 가능
			.and()
			.formLogin()
			.loginPage("/auth/login") // GET
			.defaultSuccessUrl("/");
	}
}
