package mapperInterface;

import java.util.List;

import com.example.demo.domain.JoDTO;

public interface JoMapper {

	List<JoDTO> selectList();

	JoDTO selectOne(int jno);

	int insert(JoDTO dto);

	int update(JoDTO dto);

	int delete(int jno);

	
}
