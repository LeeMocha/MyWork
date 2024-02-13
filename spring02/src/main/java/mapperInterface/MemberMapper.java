package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

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
	
}