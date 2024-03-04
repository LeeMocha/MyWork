package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Transactional // DML 을 사용할때는 트랜잭셔널 애노테이션을 붙여줘야함 (클래스단위로도 가능)
@Repository
public class MyRepositoryImpl implements MyRepository{
	
	private final EntityManager em;
	// EntityManager 을 사용해서 sql 문 사용하기
	
	public MyRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
//	@Override
	public List<Member> emMemberList2() {
		
		return em.createQuery("SELECT m FROM Member m ORDER BY id ASC", Member.class)
		.getResultList();
		// createQuery("sql문", 리턴받을 타입) 리턴타입은 TypedQuery<?>
		// builder 패턴을 사용하여 List형태로 반환 가능
		// SELECT * 의 * 을 컬룸으로 사용 불가 => 오류 발생 unexpected token
		// Alias 사용해서 컬룸에 접근해야함
		// Table 명은 여기서도 Entity 명으로 해야함
		// List 가 아닌 단일 객체로 받을 경우 .getSingleResult()
	}
	
	// => Parameter 적용
	@Override
	public List<Member> emMemberList() {
		
		return em.createQuery("SELECT m FROM Member m WHERE jno < :jno ORDER BY jno", Member.class)
		.setParameter("jno", 7)
		.getResultList();

	}
	
	public Member emMemberDetail(String id) {
		return em.createQuery("SELECT m FROM Member m WHERE id = :id",Member.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
	
} // class
