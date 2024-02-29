package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 정의

	MemberRepository repository;

	// 1) JPA Repository Method Naming 규약을 활용한 sql 구문 작성
	// => Jo 별 Member List 출력
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}

	// ** selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	} // selectList

	// ** selectOne
	@Override
	public Member selectOne(String id) {
		Optional<Member> result = repository.findById(id);
		if (result.isPresent())
			return result.get();
		else
			return null;
	} // selectOne

	// ** insert
	@Override
	public Member save(Member entity) {
		return repository.save(entity);
	} // insert

	// ** delete
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	} // delete

	@Override
	public Member pwUpdate(Member entity) {
//		return repository.pwUpdate(dto);
		return null;
	}

	@Override
	public void updatePassword(String id, String password) {
		repository.updatePassword(id, password);

	}

	// ** Join
	@Override
	public List<MemberDTO> findMemberJoin() {
		return repository.findMemberJoin();
	}

} // class
