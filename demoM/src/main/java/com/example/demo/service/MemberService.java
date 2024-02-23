package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberService {

	// ** selectList
	List<MemberDTO> selectList(); // selectList

	// ** selectOne
	MemberDTO selectOne(String id); // selectOne

	// ** insert
	int insert(MemberDTO dto); // insert

	// ** update
	int update(MemberDTO dto); // update

	// ** delete
	int delete(String id); // delete

	List<MemberDTO> selectJoList(int jno); // selectJoList
	
	int pwUpdate(MemberDTO dto); // pwUpdate

	int totalRowsCount(SearchCriteria cri); // totalRowsCount
	
	List<MemberDTO> mPageList(SearchCriteria cri); // mPageList
	
	List<MemberDTO> mCheckList(SearchCriteria cri);
	
	int checkRowsCount(SearchCriteria cri);
}