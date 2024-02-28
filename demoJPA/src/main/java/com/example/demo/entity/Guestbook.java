package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Table	// name 속성은 DB Table 과 class 명이 같으면 생략 가능
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Getter
public class Guestbook extends BaseEntity{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment 설정을 위한 애노테이션
	// ** @GeneratedValue(strategy = GenerationType.IDENTITY) 
	// => id로 설정된 기본키의 값을 자동으로 생성할때 추가적으로 사용
	// => strategy 속성: 키 생성전략
	//      - GenerationType.AUTO: default, JPA구현체 (Hibernate 등)가 생성방식 결정  
	//      - GenerationType.IDENTITY: DB가 생성방식 결정 (MySql, Maria 는 auto_increment)  
	//      - GenerationType.SEQUENCE: DB의 sequence를 이용해 생성, @SequenceGenerator 와 같이 사용  
	//      - GenerationType.TABLE: 키생성 전용 테이블을 생성해서 키생성, @TableGenerator 와 같이 사용
	@Id
	private Long gno;	// Auto_increment 시퀀스 값
	
	@Column(length = 100, nullable = false)
	private String title;
	@Column(length = 2000, nullable = false)
	private String content;
	@Column(length = 50, nullable = false)
	private String writer;
	
	public void changeTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
	
} // class
