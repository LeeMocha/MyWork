package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;

import mapperInterface.BoardMapper;
import pageTest.Criteria;
import pageTest.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<BoardDTO> selectList() {
		return mapper.selectList();
	}

	@Override
	public BoardDTO selectOne(int seq) {
		return mapper.selectOne(seq);
	}

	@Override
	public int insert(BoardDTO dto) {
		return mapper.insert(dto);
	}

	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int delete(BoardDTO dto) {
		return mapper.delete(dto);
	}

	@Override
	public int rinsert(BoardDTO dto) {
		
		if(mapper.rinsert(dto)>0) {
			// stepUpdate
			System.out.println("** stepUpdate Count => "+ mapper.stepUpdate(dto));
			return 1;
		} else return 0;
	}
	
	@Override
	public List<BoardDTO> bPageList(SearchCriteria cri) {
		// ver01.
		// return mapper.bPageList(cri);

		// ver02.
		return mapper.bSearchList(cri);
	}
	
	@Override
	public int totalRowsCount(SearchCriteria cri) {
		// ver01.
		// return mapper.totalRowsCount(cri);
		
		// ver02.
		return mapper.bSearchRowsCount(cri);
	}

	@Override
	public List<BoardDTO> bCheckList(SearchCriteria cri) {
		return mapper.bCheckList(cri);
	}

	@Override
	public int checkRowsCount(SearchCriteria cri) {
		return mapper.bSearchRowsCount(cri);
	}
	
}



