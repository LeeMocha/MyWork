package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.BoardDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

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
	
	public List<BoardDTO> bSearchList(SearchCriteria cri);
	
	public int bSearchRowsCount(SearchCriteria cri);
	
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	
	public int checkRowsCount(SearchCriteria cri);
	
	@Select("SELECT * FROM board WHERE id = #{id}")
	public List<BoardDTO> idblist(String id);
	
}
