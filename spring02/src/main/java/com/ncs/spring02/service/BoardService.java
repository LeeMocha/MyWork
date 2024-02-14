package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

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

	public List<BoardDTO> bPageList(Criteria cri);
	
	public int totalRowsCount(Criteria cri);
}
