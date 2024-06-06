package study.model.groupMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import study.model.standbyMember.StandbyMemberRequestDto;
import utill.DBManager;

public class GroupMemberDao {
	private  Connection conn;
	private  PreparedStatement pstmt;
	private  ResultSet rs;
	
	
	private GroupMemberDao() {}
	
	private static GroupMemberDao instance = new GroupMemberDao();
	
	public static GroupMemberDao getInstance() {
		return instance;
	}
	
	
	public List<String> getGroupCodeByUserCode(String userCode){
		List<String> list = null;
		
		if(userCode == null)
			return list;
		
		try {
			list = new ArrayList<>();

			conn = DBManager.getConnection();
			String sql="SELECT group_code FROM  group_member WHERE user_code=? AND accessed = true";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userCode);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				String groupCode = rs.getString(1);
				list.add(groupCode);
			}
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		return list;
	}

	 public boolean getIsMember(String groupCode, String userCode){
		boolean isMember = false;

		try{
			conn = DBManager.getConnection();
			String sql = "SELECT member_code FROM group_member WHERE group_code=? AND user_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupCode);
			pstmt.setString(2, userCode);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				isMember = true;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}

		return isMember;
	 }

	 public boolean addMemberByGroupCode(String groupCode, String userCode){
		boolean isValid = false;
		try{
			conn = DBManager.getConnection();
			String sql = "INSERT INTO group_member (group_code,user_code) VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupCode);
			pstmt.setString(2, userCode);
			pstmt.execute();
			isValid = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}

		return isValid;
	 }

	 public List<GroupMemberResponseDto> getStudyMembers(GroupMemberRequestDto groupMemberRequestDto){
		 List<GroupMemberResponseDto> list = null;

		 try{
			 String groupCode = groupMemberRequestDto.getGroupCode();

			 conn = DBManager.getConnection();
			 String sql = "SELECT gm.user_code, gm.member_code,"
			 +"(SELECT id From users u WHERE u.user_code = gm.user_code)" +
					 "FROM group_member gm WHERE group_code = ? ";

			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, groupCode);
			 rs = pstmt.executeQuery();

			 list = new ArrayList<>();
			 while(rs.next()) {
				GroupMemberResponseDto dto = new GroupMemberResponseDto();
				 String userCode = rs.getString(1);
				 String memberCode = rs.getString(2);
				 String id = rs.getString(3);

				 dto.setUserCode(userCode);
				 dto.setMemberCode(memberCode);
				 dto.setUserId(id);
				 list.add(dto);
			 }
		 }catch(SQLException e){
			e.printStackTrace();
		 }finally{
			 DBManager.close(conn, pstmt, rs);
		 }

		 return list;
	 }

	public boolean deleteGroupMember(GroupMemberRequestDto groupMemberRequestDto){
		boolean isValid = false;
		try{

			conn = DBManager.getConnection();
			String sql = "DELETE FROM group_member WHERE group_code=? AND user_code=?";
			pstmt = conn.prepareStatement(sql);

			String groupCode = groupMemberRequestDto.getGroupCode();
			String userCode = groupMemberRequestDto.getUserCode();

			pstmt.setString(1,groupCode);
			pstmt.setString(2,userCode);
			pstmt.execute();
			isValid = true;


		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
		return isValid;
	}

	public boolean joinGroupMember(GroupMemberRequestDto groupMemberRequestDto){
		boolean isValid = false;
		try{
			conn = DBManager.getConnection();
			String sql = "INSERT INTO group_member (group_code,user_code) VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);
			String groupCode = groupMemberRequestDto.getGroupCode();
			String userCode = groupMemberRequestDto.getUserCode();

			pstmt.setString(1,groupCode);
			pstmt.setString(2,userCode);
			pstmt.execute();
			isValid = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
		return isValid;
	}
	public boolean joinGroupMember(StandbyMemberRequestDto groupAwaiterRequestDto){
		boolean isValid = false;
		try{
			String groupCode = groupAwaiterRequestDto.getGroupCode();
			String userCode = groupAwaiterRequestDto.getUserCode();

			conn = DBManager.getConnection();
			String sql = "INSERT INTO group_member (group_code,user_code) VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,groupCode);
			pstmt.setString(2,userCode);
			pstmt.execute();
			isValid = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
		return isValid;
	}

	public boolean deleteGroupMemberByMemberCode(GroupMemberRequestDto groupMemberRequestDto){
		boolean isValid = false;

		try{
			String memberCode = groupMemberRequestDto.getMemberCode();

			conn = DBManager.getConnection();
			String sql = "DELETE FROM group_member WHERE member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberCode);

			pstmt.execute();
			isValid = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
		return isValid;
	}
	
}