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
	
	public List<StudyGroupResponseDto> getStudyListByGroupCodeList(List<String> groupCodes){
		List<StudyGroupResponseDto> list = null;
		
		if(groupCodes == null)
			return list;
		
		
		try {
			list = new ArrayList<>();
			
			conn = DBManager.getConnection();
			
			for(String groupCode : groupCodes) {
				String sql = "SELECT * FROM study_group WHERE group_code =?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,groupCode);
				
				rs = pstmt.executeQuery();
				
				String name = rs.getString(2);
				String decription =  rs.getString(3);
				String adminCode =  rs.getString(4);
				String isPublic =  rs.getString(5).equals("0") ? "false" : "true";
				
				StudyGroupResponseDto sg = new StudyGroupResponseDto(groupCode, name, decription,adminCode,isPublic);
				
				list.add(sg);
			}
			
			System.out.println("DB 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

}
