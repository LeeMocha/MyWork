package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberDSLRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	// ** 전역변수 정의

	private final MemberRepository repository;
	private final MyRepository emrepository;
	private final MemberDSLRepository dslrepository;
	

	// 1) JPA Repository Method Naming 규약을 활용한 sql 구문 작성
	// => Jo 별 Member List 출력
	@Override
	public List<Member> findByJno(int jno) {
//		return repository.findByJno(jno);
		return dslrepository.findMemberJnoDSL(jno);
	}

	// ** selectList
	@Override
	public List<Member> selectList() {
//		return repository.findAll();	// ver01
		return emrepository.emMemberList(); // ver02: entityManager Test
	} // selectList

	// ** selectOne
	@Override
	public Member selectOne(String id) {
//		Optional<Member> result = repository.findById(id);
		
		Member result = emrepository.emMemberDetail(id);
		
//		if (result.isPresent())
		if(result != null)
			return result;
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
//		return repository.findMemberJoin();
		return dslrepository.findMemberJoinDSL();
	}

} // class
