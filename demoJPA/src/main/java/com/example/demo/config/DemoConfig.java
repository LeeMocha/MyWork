package com.example.demo.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
// => Spring 에서 얘를 설정파일로 인식 할 수 있음 .xml @ 와 같이
public class DemoConfig {
// => 일반적인 Bean 설정용

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ** QueryDSL 사용을 위한 설정
	// => EntityManager
	// => JPAQueryFactory를 Bean 등록하여 프로젝트 전역에서 QueryDSL을 작성할 수 있도록 함.
	@PersistenceContext
	// => EntityManager 객체 주입 애너테이션 (@Bean으로 생성하지 않음, 위 에노테이션이 전용이라 보면됨)
	// => SpringBoot JPA 에서는 엔티티 매니저 팩토리 관련 부분을 작성하지 않아도 생성 & 주입 해줌
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}

}
