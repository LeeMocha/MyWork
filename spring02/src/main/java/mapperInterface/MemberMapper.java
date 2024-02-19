package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberMapper {
	
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
	
	int totalRowsCount(SearchCriteria cri);
	
	List<MemberDTO> mPageList(SearchCriteria cri);
	
	List<MemberDTO> mSearchList(SearchCriteria cri);
	
	int mSearchRowsCount(SearchCriteria cri);
	
	List<MemberDTO> mCheckList(SearchCriteria cri);
	
	int checkRowsCount(SearchCriteria cri);
	
	// ** JUnit Test
	// => selectDTO, xml 대신 @ 으로 Sql 구현 가능
	@Select("SELECT * FROM member WHERE id = #{id}")
	MemberDTO selectDTO(MemberDTO dto);
	
	// => @Param 적용 Test
	//   -> 기본규칙: Mybatis 에서는 매개변수 Type은 무관하지만, 갯수는 1개만 허용
	//   -> @Param: mapper 에서 #{...} 적용, 복수갯수 사용 가능 (단, 기본자료형 사용불가_JUnit 에서는 가능) 
	@Select("SELECT * FROM member WHERE id = #{ii} AND jno = #{jno}")
	MemberDTO selectParam(@Param("ii") String id, @Param("jno") int jno);
	
}
