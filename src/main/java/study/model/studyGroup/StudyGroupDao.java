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
	PreparedStatement pstmt2;
	ResultSet rs;
	ResultSet rs2;
	
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

	public StudyGroupResponseDto getPopularStudyByGroupCode(String groupCode){

		StudyGroupResponseDto study = null;

		if(groupCode == null)
			return study;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT name,decription, admin_code, is_public,auto_member_access FROM study_group WHERE group_code =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,groupCode);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString(1);
				String decription =  rs.getString(2);
				String adminCode =  rs.getString(3);
				String isPublic =  rs.getString(4).equals("0") ? "false" : "true";
				String autoMemberAccess = rs.getString(5).equals("0") ? "false" : "true";

				if(decription == null)
					study = new StudyGroupResponseDto(groupCode, name,adminCode,isPublic,autoMemberAccess);
				else
					study = new StudyGroupResponseDto(groupCode, name,decription,adminCode,isPublic,autoMemberAccess);
			}

			System.out.println("DB 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}

		return study;
	}
	public StudyGroupResponseDto getStudyByGroupCode(String groupCode){

		StudyGroupResponseDto study = null;
		
		if(groupCode == null)
			return study;
		
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT name,decription, admin_code, is_public,auto_member_access FROM study_group WHERE group_code =?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1,groupCode);

			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				String name = rs2.getString(1);
				String decription =  rs2.getString(2);
				String adminCode =  rs2.getString(3);
				String isPublic =  rs2.getString(4).equals("0") ? "false" : "true";
				String autoMemberAccess = rs2.getString(5).equals("0") ? "false" : "true";

				if(decription == null)
					study = new StudyGroupResponseDto(groupCode, name,adminCode,isPublic,autoMemberAccess);
				else
					study = new StudyGroupResponseDto(groupCode, name,decription,adminCode,isPublic,autoMemberAccess);
			}
			
			System.out.println("DB 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt2, rs2);
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

	public boolean createStudy(String name, String adminCode, String decription, boolean isPublic, boolean autoMemberAccess){
		boolean isValid = false;

		try{
			conn = DBManager.getConnection();
			String sql = "INSERT INTO study_group (name,admin_code,decription,is_public,auto_member_access) " +
					"VALUES (?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,adminCode);
			pstmt.setString(3,decription);
			pstmt.setBoolean(4,isPublic);
			pstmt.setBoolean(5,autoMemberAccess);

			pstmt.execute();
			isValid = true;
			System.out.println("DB연동 성공");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("DB연동 실패");
		}finally {
			DBManager.close(conn, pstmt);
		}

		return isValid;
	}

	public String createStudyAndGetGroupCode(String name, String adminCode, String decription, boolean isPublic, boolean autoMemberAccess){
		String code = null;

		boolean isValid = createStudy(name,adminCode,decription,isPublic,autoMemberAccess);

		if(!isValid)
			return code;

		try{
			conn = DBManager.getConnection();
			if(decription == null){
				String sql = "SELECT group_code FROM study_group WHERE name = ? AND admin_code =? AND decription IS NULL AND is_public =? AND auto_member_access =?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,adminCode);
				pstmt.setBoolean(3,isPublic);
				pstmt.setBoolean(4,autoMemberAccess);
				System.out.println(pstmt.getResultSet());
				rs = pstmt.executeQuery();
			}else {
				String sql = "SELECT group_code FROM study_group WHERE name = ? AND admin_code =? AND decription =? AND is_public =? AND auto_member_access =?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,adminCode);
				pstmt.setString(3, decription);
				pstmt.setBoolean(4,isPublic);
				pstmt.setBoolean(5,autoMemberAccess);
				System.out.println(pstmt.getResultSet());
				rs = pstmt.executeQuery();
			}

			if(rs.next()) {
				code = rs.getString(1);
			}
			System.out.println("DB연동 성공");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("DB연동 실패");
		}finally {
			DBManager.close(conn, pstmt);
		}

		return code;
	}


}
