package com.example.demo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ** DTO, VO, Entity
// => MemberJoDTO 확인

// ** Spring Security Role 인증 적용
// => 스프링시큐리티 의 Role을 적용하기 위해서는 스프링시큐리티가 사용하는 형식의 객체로 만들어야함.
//	-> org.springframework.security.core.userdetails.User 를 상속해야함.
//	-> User클래스(조상) 의 생성자를 이용해 조상의 컬럼들까지 모두 초기화 하는 생성자 작성. 
//	   즉, super(~,~,~) 호출하는 생성자 작성 필수

@Data
public class MemberDTO extends User {
	
	private static final long serialVersionUID = 1L;
	
	// 1) private 맴버변수
	private String id; // Primary_Key
	private String password; // not null
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String birthday;
	private String rid; //추천인
	private String uploadfile; // Table 보관용(File_Name)
	
	// => Role 목록 추가
	private List<String> roleList = new ArrayList<>();

	// => Security 인증을 위한 생성자 
	public MemberDTO(String id, String pw, String name, int age, int jno, 
					String info, double point, String birthday, String rid, 
					String uploadfile, List<String> roleNames) {
		super(id, pw, 
			  roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str))
			  .collect(Collectors.toList()));
		
		this.id = id;
		this.password = pw;
		this.name = name;
		this.age = age;
		this.jno = jno;
		this.info = info;
		this.point = point;
		this.birthday = birthday;
		this.rid = rid;
		this.uploadfile = uploadfile;
	} //생성자 
	
} //class
