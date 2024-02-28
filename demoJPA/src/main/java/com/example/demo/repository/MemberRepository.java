package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

public interface MemberRepository 
					extends JpaRepository<Member, String> {
	// 제네릭인자 : 첫번째=>Table(Entity) 명 , primary Key 의 타입
	
}
