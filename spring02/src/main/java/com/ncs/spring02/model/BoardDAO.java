package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

// ** 게시판
// => CRUD 구현

@Repository
public class BoardDAO {
	// ** 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// ** selectList()
	public List<BoardDTO> selectList() {
		sql = "SELECT * FROM board ORDER BY root DESC, step ASC";
		// => 답글달기 추가 후 출력순서 수정
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();

					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));

					list.add(dto);

				} while (rs.next());

			} else {
				System.out.println("** Board selectList => 출력자료가 없습니다 **");
				return null;
			}

			return list;

		} catch (Exception e) {
			System.out.println("** Board selectList => " + e.toString());
			return null;
		}
	} // selectList

	// ** selectOne()
	public BoardDTO selectOne(int seq) {
		sql = "SELECT * FROM board WHERE seq = " + seq;
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
				
				return dto;
			} else {
				System.out.println("** Board selectOne => 출력할 자료가 없습니다 **");
			}
			
		} catch (Exception e) {
			System.out.println("** Board selectOne => " + e.toString());
			return null;
		}
		
		return null;
	} // selectOne

	 // ** insert : 원글입력
	 // => 입력 컬럼: id, title, content 
	 //    default값: regdate, cnt, step, indent
	 // => root : seq 와 동일한 값   
	 // => Auto_Inc: seq (계산: auto 보다 IFNULL(max(seq)...) 를 적용)
	public int insert(BoardDTO dto) {
		sql = "INSERT INTO board VALUES (\r\n"
				+ "( SELECT * FROM (SELECT ifNull(max(seq),0)+1 FROM board) AS temp ) , \r\n"
				+ "?, ?, ?, CURRENT_TIMESTAMP, 0, \r\n"
				+ "( SELECT * FROM (SELECT ifNull(max(seq),0)+1 FROM board) AS temp ) , 0, 0 )";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board Insert => "+ e.toString());
		}
		
		return 0;
	} // insert

	// ** update()
	public int update(BoardDTO dto) {
		sql = "UPDATE board SET title = ? , content = ? , cnt = ? WHERE seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setInt(3, dto.getCnt());
			pst.setInt(4, dto.getSeq());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** Board Update => " + e.toString());
			return 0;
		}
	} // update

	// ** delete()
	public int delete(int seq) {
		sql = "DELETE FROM board WHERE seq = " + seq;
		try {
			pst = cn.prepareStatement(sql);
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** Board Delete => "+ e.toString());
			return 0;
		}
	} // delete

	// ** replyInsert : 답글입력
	// => seq: IFNULL 이용
	// => 입력 컬럼: id, title, content, root, step, indent
	// => JDBC subQuery 구문 적용시 주의사항
	//     -> MySql: select 구문으로 한번더 씌워 주어야함 (insert 의 경우에도 동일)   
	// => stepUpdate 가 필요함
	//    댓글 입력 성공후 실행
	//     -> 현재 입력된 답글의 step 값은 수정되지 않도록 sql 구문의 조건 주의   
	// => boardList 의 출력 순서 확인
	// 	  ~~~ order by root desc, step asc
	public int rinsert(BoardDTO dto) {
		sql = "INSERT INTO board(seq, id, title, content, root, step, indent) VALUES (\r\n"
			+ "( SELECT * FROM (SELECT ifNull(max(seq),0)+1 FROM board) AS temp ) , \r\n"
			+ "?, ?, ?, ?, ?, ?)";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setInt(4, dto.getRoot());
			pst.setInt(5, dto.getStep());
			pst.setInt(6, dto.getIndent());
			pst.executeUpdate();	// 답글등록 성공 -> stepUpdate
			System.out.println("** stepUpdate Count => "+ stepUpdate(dto));
				
			return 1;
		} catch (Exception e) {
			System.out.println("** Reply Insert => "+ e.toString());
			return 0;
		}
	} // rinsert

	// ** stepUpdate : step 값 증가
	// => 조건
	// 	-> root 동일 step >= new and 새 글은 제외
	public int stepUpdate(BoardDTO dto) {
		sql = "UPDATE board SET step = step+1 WHERE root=? AND step >= ? "
				+ "AND seq <> ( SELECT * FROM (SELECT ifNull(max(seq),0) FROM board) AS temp )";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getRoot());
			pst.setInt(2, dto.getStep());
			
			return pst.executeUpdate(); // 수정된 Data 갯수 return
			
		} catch (Exception e) {
			System.out.println("** step Update =>" + e.toString());
			return 0;
		}
	} // stepUpdate
	
} // BoardDAO
