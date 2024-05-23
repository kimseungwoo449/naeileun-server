package study.model.studyGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utill.DBManager;

public class StudyGroupDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private StudyGroupDao() {}
	
	private static StudyGroupDao instance = new StudyGroupDao();
	
	public static StudyGroupDao getInstance() {
		return instance;
	}
	
	public StudyGroupResponseDto getStudyByGroupCode(String groupCode){
		StudyGroupResponseDto study = null;
		
		if(groupCode == null)
			return study;
		
		try {
			study = new StudyGroupResponseDto();
			
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM study_group WHERE group_code =?";
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1,groupCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString(2);
				String decription =  rs.getString(3);
				String adminCode =  rs.getString(4);
				String isPublic =  rs.getString(5).equals("0") ? "false" : "true";
				
				study = new StudyGroupResponseDto(groupCode, name, decription == null ? "" : decription,adminCode,isPublic);
			}
			
			System.out.println("DB 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		
		return study;
	}
	
	
	public List<String> getStudyBoardByGroupCode(String groupCode){
		List<String> list = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT group_code, name, admin_code,is_public, decription,(SELECT )";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<>();
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}

}
