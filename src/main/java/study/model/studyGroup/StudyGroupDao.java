package study.model.studyGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import study.model.groupMember.GroupMemberDao;
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

	public List<StudyGroupResponseDto> findMyStudyAllByUserCode(String userCode){

		List<StudyGroupResponseDto> list = new ArrayList<>();
		GroupMemberDao gmDao = GroupMemberDao.getInstance();
		List<String> groupCodes = gmDao.getGroupCodeByUserCode(userCode);
			
		for(String code : groupCodes) {
			StudyGroupResponseDto study = getStudyByGroupCode(code);
			list.add(study);
		}
		return list;
	}

	
	public StudyGroupResponseDto getStudyByGroupCode(String groupCode){

		StudyGroupResponseDto study = null;
		
		if(groupCode == null)
			return study;
		
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT name,decription, admin_code, is_public FROM study_group WHERE group_code =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,groupCode);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String name = rs.getString(1);
				String decription =  rs.getString(2);
				String adminCode =  rs.getString(3);
				String isPublic =  rs.getString(4).equals("0") ? "false" : "true";

				if(decription == null)
					study = new StudyGroupResponseDto(groupCode, name,adminCode,isPublic);
				else
					study = new StudyGroupResponseDto(groupCode, name,decription,adminCode,isPublic);
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

			while(rs.next()) {
				list = new ArrayList<>();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public boolean deleteStudyByGroupCode(String groupCode){
		boolean isValid = false;
		try{
			conn = DBManager.getConnection();
			String sql = "DELETE FROM study_group WHERE group_code =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,groupCode);

			System.out.println("excute result : " +pstmt.execute());
			isValid = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}

		return isValid;
	}

}
