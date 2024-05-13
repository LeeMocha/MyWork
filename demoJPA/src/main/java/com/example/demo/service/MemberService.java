package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.MemberJoDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	
	// ** 스프링시큐리티, JWT 인증
	Member getWithRoles(String id);
	
	// ** Join
	List<MemberDTO> findMemberJoin();
	
	// ** Password Update
	// => @Query 적용
	void updatePassword(String id, String password);
	
	// ** jno별 Member 출력
	// => JPARepository Method Naming 규약
	List<Member> findByJno(int jno);
	
	// ** selectList
	List<Member> selectList();

	// ** selectOne
	Member selectOne(String id);

	// ** insert, update
	Member save(Member entity);
	 
	// ** Password_Update
	Member pwUpdate(Member entity);

	// ** delete
	void deleteById(String id);

}