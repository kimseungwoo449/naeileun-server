package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import user.model.UserDao;
import utill.DBManager;

public class BoardDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDao() {
		
	}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	public List<BoardResponseDto> readAllPost(){
		List<BoardResponseDto> postList = new ArrayList<BoardResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			UserDao userDao = UserDao.getInstance();
			
			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_code, write_date, update_date, MAX(recommandation), post_code FROM board GROUP BY board_code";
			
			pstmt =  conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardCode = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3) == null ? "" : rs.getString(3);
				Timestamp createdDate = rs.getTimestamp(4);
				String title = rs.getString(5);
				String content = rs.getString(6);
//				String userId = userDao(rs.getInt(7));
				String userId = "1234";
				Timestamp writeDate = rs.getTimestamp(8);
				Timestamp updateDate = rs.getTimestamp(9);
				int recommandation = rs.getInt(10);
				int postCode = rs.getInt(11);
				
				BoardResponseDto board = new BoardResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommandation, postCode);
				postList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return postList;
	}
}
