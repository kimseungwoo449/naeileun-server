package study.model.studyGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import study.model.groupMember.GroupMemberDao;
import study.model.groupMember.GroupMemberRequestDto;
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

	public List<StudyGroupResponseDto> getPopularStudy(){
		List<StudyGroupResponseDto> list = new ArrayList<>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT group_code,name,decription,admin_code,is_public,auto_member_access FROM popular_studys";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				String groupCode = rs.getString(1);
				String name = rs.getString(2);
				String decription =  rs.getString(3);
				String adminCode =  rs.getString(4);
				String isPublic =  rs.getString(5).equals("0") ? "false" : "true";
				String autoMemberAccess = rs.getString(6).equals("0") ? "false" : "true";
				System.out.println("name ; " + name);
				if(decription == null) {
					StudyGroupResponseDto study = new StudyGroupResponseDto(groupCode, name, adminCode, isPublic, autoMemberAccess);
					list.add(study);
				}
				else {
					StudyGroupResponseDto study = new StudyGroupResponseDto(groupCode, name, decription, adminCode, isPublic, autoMemberAccess);
					list.add(study);
				}
			}

			System.out.println("DB 연동 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}
	public StudyGroupResponseDto getStudyByGroupCode(String groupCode){

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

	public List<StudyGroupResponseDto> getUserStudyByUserCode(GroupMemberRequestDto gmReqDto){
		List<StudyGroupResponseDto> list = new ArrayList<>();
		try{
			String userCode = gmReqDto.getUserCode();

			conn = DBManager.getConnection();
			String sql = "SELECT gm.group_code, sg.name, sg.decription, sg.admin_code, sg.is_public, sg.auto_member_access FROM group_member gm JOIN study_group sg ON gm.group_code = sg.group_code WHERE gm.user_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String groupCode = rs.getString(1);
				String groupName = rs.getString(2);
				String decription = rs.getString(3);
				String adminCode = rs.getString(4);
				String isPublic = rs.getString(5);
				String autoMemberAccess = rs.getString(6);

				StudyGroupResponseDto sg = new StudyGroupResponseDto(groupCode,groupName,decription,adminCode,isPublic,autoMemberAccess);
				list.add(sg);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
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
