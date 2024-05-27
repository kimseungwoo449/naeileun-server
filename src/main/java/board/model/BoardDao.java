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
	
	public BoardResponseDto createBoard(BoardRequestDto boardDto) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "";
			
			if(boardDto.getDescription() != null) {
				sql = "INSERT INTO board_category(board_name, description) VALUES(?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boardDto.getBoardName());
				pstmt.setString(2, boardDto.getDescription());
			} else {
				sql = "INSERT INTO board_category(board_name) VALUES(?)";

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boardDto.getBoardName());
			}
			pstmt.execute();
			
			return findBoardByName(boardDto.getBoardName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return null;
	}
	
	public BoardResponseDto findBoardByName(String name) {
		BoardResponseDto board = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT board_code, board_name, description, created_date FROM board_category WHERE board_name=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int boardCode = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3) == null ? "" : rs.getString(3);
				Timestamp createdDate = rs.getTimestamp(4);
				
				if(name.equals(boardName)) {
					board = new BoardResponseDto(boardCode, boardName, description, createdDate);
					return board;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	public List<BoardResponseDto> readBestPost(){
		List<BoardResponseDto> postList = new ArrayList<BoardResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			UserDao userDao = UserDao.getInstance();
			
			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_code, write_date, update_date, recommandation, rank() over (order by recommandation desc) AS ranking, post_code FROM board LIMIT 3";
			
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
				int postCode = rs.getInt(12);
				
				BoardResponseDto post = new BoardResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommandation, postCode);
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return postList;
	}
	
	public List<BoardResponseDto> readAllBoard(){
		List<BoardResponseDto> boardList = new ArrayList<BoardResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT board_code, board_name, description, created_date FROM board_category";
			
			pstmt =  conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardCode = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3) == null ? "" : rs.getString(3);
				Timestamp createdDate = rs.getTimestamp(4);
				
				BoardResponseDto board = new BoardResponseDto(boardCode, boardName, description, createdDate);
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return boardList;
	}
	
	public List<BoardResponseDto> readAllPostByBoardCode(int code){
		List<BoardResponseDto> postList = new ArrayList<BoardResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			UserDao userDao = UserDao.getInstance();
			
			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_code, write_date, update_date, recommandation, post_code FROM board WHERE board_code=?";
			
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			
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
				
				BoardResponseDto post = new BoardResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommandation, postCode);
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return postList;
	}
	
	public BoardResponseDto readPostByBoardCodeAndPostCode(int boardCodeTemp, int postCodeTemp) {
		BoardResponseDto post = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT title, content, user_code, write_date, update_date, recommandation, post_code, board_code FROM posts WHERE post_code=? AND board_code=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCodeTemp);
			pstmt.setInt(2, postCodeTemp);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				int userCode = rs.getInt(3);
				Timestamp writeDate = rs.getTimestamp(4);
				Timestamp updateDate = rs.getTimestamp(5);
				int recommandation = rs.getInt(6);
				int postCode = rs.getInt(7);
				int boardCode = rs.getInt(8);
				
				post = new BoardResponseDto(title, content, userCode, writeDate, updateDate, recommandation, postCode, boardCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return post;
	}
}
