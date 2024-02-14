package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

public interface BoardMapper {

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
	
	// ** rinsert()
	public int rinsert(BoardDTO dto);

	// ** stepUpdate()
	public int stepUpdate(BoardDTO dto);
	
	public List<BoardDTO> bPageList(Criteria cri);
	
	public int totalRowsCount(Criteria cri);
}
