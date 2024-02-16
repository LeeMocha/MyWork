package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

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