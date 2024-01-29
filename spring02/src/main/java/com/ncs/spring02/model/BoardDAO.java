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
		sql = "SELECT * FROM board ORDER BY seq DESC";
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

	// ** insert()
	public int insert(BoardDTO dto) {
		sql = "INSERT INTO board VALUES (\r\n"
				+ "( SELECT * FROM (SELECT ifNull(max(seq),0)+1 FROM board) AS temp ) , \r\n"
				+ "?, ?, ?, CURRENT_TIMESTAMP, 0, \r\n"
				+ "( SELECT * FROM (SELECT ifNull(max(seq),0)+1 FROM board) AS temp ) , 0, 0 );	";
		
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

		return 0;
	} // update

	// ** delete()
	public int delete(int seq) {

		return 0;
	} // delete

} // BoardDAO
