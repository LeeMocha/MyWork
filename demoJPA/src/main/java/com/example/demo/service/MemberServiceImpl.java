/*
 * package com.example.demo.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.example.demo.domain.MemberDTO; import
 * com.example.demo.entity.Member; import
 * com.example.demo.repository.MemberRepository;
 * 
 * import pageTest.SearchCriteria;
 * 
 * //** Service //=> 요청클래스 와 DAO클래스 사이의 연결(완충지대) 역할 //=> 요청클래스(컨트롤러) 와 DAO클래스
 * 사이에서 변경사항이 생기더라도 서로 영향 받지않도록해주는 역할 //결합도는 낮추고, 응집도는 높인다
 * 
 * //** interface 자동완성 //=> Alt + Shift + T //=> 또는 마우스우클릭 PopUp Menu 의 Refactor
 * - Extract Interface...
 * 
 * 
 * //** Mybatis 적용 //=> CRUD 처리를 Mapper 를 이용 //=> DAO 대신 Mapper interface ->
 * ~Mapper.xml //=> xml의 경우엔 resources 패키지에서 찾게되니 거기다 패키지명도 똑같이 해서 만들어주어야 함.
 * 
 * //** Mybatis interface 방식으로 적용 //=> MemberDAO 대신 MemberMapper 사용 //=>
 * MemberMapper 의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함 //(스프링이 생성해주는 동일한 타입의 클래스는 JUnit
 * Test 로 확인가능, 추후 실습) //=> 단, 설정화일에 <mybatis-spring:scan
 * base-package="mapperInterface"/> 반드시 추가해야함 // MemberDAO의 Sql구문 처리를
 * mapperInterface 사용으로 MemberMapper 가 역할을 대신함
 * 
 * //=> SQL 구문 : xml 로작성 -> 이 화일을 Mapper 라 함 //=> Mapper 작성규칙 // ->
 * mapperInterface 와 패키지명, 화일명이 동일해야함 // -> 즉, Java interface, Mapper, Mapper 의
 * namespace 에 들어가는 이름에 package 및 파일명이 다 동일해야함. // -> 그리고 구현할 method는 Mapper.xml
 * 구문의 id 속성 값으로 찾음.
 * 
 * @Service public class MemberServiceImpl implements MemberService { // ** 전역변수
 * 정의
 * 
 * @Autowired private final MemberRepository repository;
 * 
 * // ** selectList
 * 
 * @Override public List<Member> selectList(){ return repository.findAll(); } //
 * selectList
 * 
 * 
 * // ** selectOne
 * 
 * @Override public MemberDTO selectOne(String id) { return
 * mapper.selectOne(id); } // selectOne
 * 
 * 
 * // ** insert
 * 
 * @Override public int insert(MemberDTO dto) { return mapper.insert(dto); } //
 * insert
 * 
 * 
 * // ** update
 * 
 * @Override public int update(MemberDTO dto) { return mapper.update(dto); } //
 * update
 * 
 * 
 * // ** delete
 * 
 * @Override public int delete(String id) { return mapper.delete(id); } //
 * delete
 * 
 * @Override public List<MemberDTO> selectJoList(int jno){ return
 * mapper.selectJoList(jno); } // selectJoList
 * 
 * 
 * @Override public int pwUpdate(MemberDTO dto) { return mapper.pwUpdate(dto); }
 * 
 * 
 * @Override public List<MemberDTO> mPageList(SearchCriteria cri) { return
 * mapper.mSearchList(cri); }
 * 
 * @Override public int totalRowsCount(SearchCriteria cri) { return
 * mapper.mSearchRowsCount(cri); }
 * 
 * @Override public List<MemberDTO> mCheckList(SearchCriteria cri) { return
 * mapper.mCheckList(cri); }
 * 
 * @Override public int checkRowsCount(SearchCriteria cri) { return
 * mapper.checkRowsCount(cri); }
 * 
 * 
 * @Override public int axidelete(String id) { return mapper.axidelete(id); }
 * 
 * } // class
 */