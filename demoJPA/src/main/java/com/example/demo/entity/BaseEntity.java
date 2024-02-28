package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

//** BaseEntity
//=> 자료 등록시간, 수정시간 등 자동으로 추가되고 변경되는 값들을 자동으로 처리하기위한 BaseEntity 클래스 
//=> 추상클래스로 작성     
//=> @MappedSuperclass: 테이블로 생성되지않음
//=> @EntityListeners : 엔티티객체의 변화를 감지하는 리스너설정 (AuditingEntityListener.class 가 담당)
// AuditingEntityListener 를 활성화 시키기 위해서는 
//  DemoJpaApplication.java 에 @EnableJpaAuditing 설정추가해야함.

@MappedSuperclass											// 슈퍼 클래스로서의 역할이다 라는 것을 명시
@EntityListeners(value = {AuditingEntityListener.class})	// 엔티티가 변화될때 보여질 수 있도록 하는
@Getter														// 시간을 주입받을 것이기에 setter 가 필요없음
abstract class BaseEntity {
	
	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;	// 입력된 시간, 처음만 들어가고 수정되면 안되기에 updatable=false
	
	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modDate;	// 수정된 시간
	
}
