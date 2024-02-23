package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// => Spring 에서 얘를 설정파일로 인식 할 수 있음 .xml @ 와 같이
public class DemoConfig {
// => 일반적인 Bean 설정용
	
	@Bean
	public PasswordEncoder getPasswordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
}
