package study.model.groupMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utill.DBManager;

public class GroupMemberDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
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
			conn = DBManager.getConnection();
			String sql="SELECT group_code FROM  group_member WHERE user_code=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userCode);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				String groupCode = rs.getString(1);
				list.add(groupCode);
			}
			
			System.out.println("DB연동 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		return list;
	}
	
	
	
}
