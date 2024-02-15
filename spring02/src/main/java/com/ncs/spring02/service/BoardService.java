package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface BoardService {
	
	
	// ** selectList()
	public List<BoardDTO> selectList();
	
	
	// ** selectOne()
	public BoardDTO selectOne(int seq);
	
	
	// ** insert()
	public int insert(BoardDTO dto);
	
	
	// ** update()
	public int update(BoardDTO dto);
	
	// ** delete()
	public int delete(BoardDTO dto);
	
	public int rinsert(BoardDTO dto);

	public List<BoardDTO> bPageList(SearchCriteria cri);
	
	public int totalRowsCount(SearchCriteria cri);
	
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	
	public int checkRowsCount(SearchCriteria cri);
	
}
