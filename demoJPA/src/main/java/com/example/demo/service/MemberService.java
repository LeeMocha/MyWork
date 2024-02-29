package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {

	// ** findByJno
	List<Member> findByJno(int jno);

	// ** selectList
	List<Member> selectList(); // selectList

	// ** selectOne
	Member selectOne(String id); // selectOne

	// ** insert
	Member save(Member entity); // insert & update

	// ** delete
	void deleteById(String id); // delete

	Member pwUpdate(Member entity); // pwUpdate

	// ** Password Update
	// => @Query 적용
	void updatePassword(String id, String password);

	// ** Join
	List<MemberDTO> findMemberJoin();

}