package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;

@Repository
public class JoDAO {
	// ** 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
//	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** selectList
	public List<JoDTO> selectList (){
		sql = "SELECT j.jno, j.jname, j.captain, m.name, j.project, j.slogan FROM jo j, member m"
				+ " WHERE j.captain = m.id";
		List<JoDTO> list = new ArrayList<JoDTO>();
		try {

			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				do {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setCname(rs.getString(4));
					dto.setProject(rs.getString(5));
					dto.setSlogan(rs.getString(6));

					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			} // else
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		} // try
	} // selectList
	
	// ** selectOne
	public JoDTO selectOne (int jno){
		sql = "SELECT * FROM jo WHERE jno = ?";
		try {
			
			pst = cn.prepareStatement(sql);
			pst.setInt(1,jno);
			rs = pst.executeQuery();
			
			if (rs.next()) {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setCaptain(rs.getString(3));
					dto.setProject(rs.getString(4));
					dto.setSlogan(rs.getString(5));
				return dto;
			} else {
				return null;
			} // else
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		} // try
	} // selectOne
	
	// ** insert
	public int insert(JoDTO dto) {
		sql = "INSERT INTO jo VALUES (?,?,?,?,?)";
		System.out.println(dto);
		try {
			pst = cn.prepareStatement(sql);
			
			
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getJname());
			pst.setString(3, dto.getCaptain());
			pst.setString(4, dto.getProject());
			pst.setString(5, dto.getSlogan());
			
			return pst.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("** Insert Exception => " + e.toString());
			return 0;
		}
	} // insert
	
	public int update(JoDTO dto) {
		sql = "UPDATE jo SET jname=?"
				+ ", captain=?, project=?, slogan=? WHERE jno=?";
		try {
			pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getJname());
			pst.setString(2, dto.getCaptain());
			pst.setString(3, dto.getProject());
			pst.setString(4, dto.getSlogan());
			pst.setInt(5, dto.getJno());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("** Update Exception => " + e.toString());
			return 0;
		}
	} // update
	
	// ** delete
	public int delete(int jno) {
		sql = "DELETE FROM jo WHERE jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			
			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Delete Exception => " + e.toString());
			return 0;
		}
	} // delete
	
} // class 
