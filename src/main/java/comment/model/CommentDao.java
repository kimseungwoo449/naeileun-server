package comment.model;

import user.model.UserDao;
import utill.DBManager;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static final String UPLOAD_DIR = "src/main/java/board/image";

	private CommentDao() {
		
	}
	
	private static CommentDao instance = new CommentDao();
	
	public static CommentDao getInstance() {
		return instance;
	}
	
//	public CommentResponseDto createPost(String reqTitle, String reqContent, String reqUserId, String reqBoardCode, String reqImagePath) {
//		CommentResponseDto responseDto = null;
//
//		UserDao userDao = UserDao.getInstance();
//		int userCode = userDao.findUserCodeById(reqUserId);
//		System.out.println("userCode : " + userCode);
//
//		boolean isSuccess = false;
//		try {
//			conn = DBManager.getConnection();
//
//			String sql = "INSERT INTO posts(title, content, user_code, board_code, image_path) VALUES(?,?,?,?,?)";
//
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, reqTitle);
//			pstmt.setString(2, reqContent);
//			pstmt.setInt(3, userCode);
//			pstmt.setInt(4, Integer.parseInt(reqBoardCode));
//			pstmt.setString(5, reqImagePath);
//
//			pstmt.execute();
//			isSuccess = true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt);
//
//			if(isSuccess) {
//				responseDto = findBoardByUserCodeLatest(userCode);
//			}
//		}
//
//		return responseDto;
//	}
//
//	private CommentResponseDto findBoardByUserCodeLatest(int userCode) {
//		CommentResponseDto responseDto = null;
//
//		String sql = "SELECT * FROM post_res WHERE user_code=? ORDER BY write_date DESC LIMIT 1";
//
//		try {
//			conn = DBManager.getConnection();
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, userCode);
//
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				String title = rs.getString(1);
//				String content = rs.getString(2);
//				String userId = rs.getString(4);
//				Timestamp writeDate = rs.getTimestamp(5);
//				Timestamp updateDate = rs.getTimestamp(6);
//				int recommendation = rs.getInt(7);
//				int postCode = rs.getInt(8);
//				int boardCode = rs.getInt(9);
//				String imagePath = rs.getString(10);
//
//				responseDto = new CommentResponseDto(title, content, userId, writeDate, updateDate, recommendation, postCode, boardCode);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return responseDto;
//	}
//
///*	public BoardResponseDto createPost(String reqTitle, String reqContent, String reqUserId, String reqBoardCode) {
//		try {
//			conn = DBManager.getConnection();
//			UserDao userDao = UserDao.getInstance();
//
//			String sql = "INSERT INTO posts(title, content, user_code, board_code) VALUES(?,?, ?, ?)";
//
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, reqTitle);
//			pstmt.setString(2, reqContent);
//			pstmt.setInt(3, userDao.findUserCodeById(reqUserId));
//			pstmt.setInt(4, Integer.parseInt(reqBoardCode));
//
//			pstmt.execute();
//
//			String[] tempCode = findPostCodeAndBoardCodeRecently().split("/");
//
//			int postCode = Integer.parseInt(tempCode[0]);
//			int boardCode = Integer.parseInt(tempCode[1]);
//
//			return readPostByBoardCodeAndPostCode(boardCode, postCode);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt);
//		}
//
//		return null;
//	}*/
//
//	public String findPostCodeAndBoardCodeRecently() {
//		String postAndBoardCode = "";
//
//		try {
//			conn = DBManager.getConnection();
//
//			String sql = "SELECT post_code, board_code FROM posts ORDER BY write_date DESC LIMIT 1";
//
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				int postCode = rs.getInt("post_code");
//				int boardCode = rs.getInt("board_code");
//
//				postAndBoardCode = postCode + "/" + boardCode;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return postAndBoardCode;
//	}
//
//	public CommentResponseDto findBoardByName(String name) {
//		CommentResponseDto board = null;
//
//		try {
//			conn = DBManager.getConnection();
//
//			String sql = "SELECT board_code, board_name, description, created_date FROM board_category WHERE board_name=?";
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, name);
//
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				int boardCode = rs.getInt(1);
//				String boardName = rs.getString(2);
//				String description = rs.getString(3) == null ? "" : rs.getString(3);
//				Timestamp createdDate = rs.getTimestamp(4);
//
//				if(name.equals(boardName)) {
//					board = new CommentResponseDto(boardCode, boardName, description, createdDate);
//					return board;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return null;
//	}
//
//	public List<CommentResponseDto> readBestPost(){
//		List<CommentResponseDto> postList = new ArrayList<CommentResponseDto>();
//
//		try {
//			conn = DBManager.getConnection();
//			UserDao userDao = UserDao.getInstance();
//
//			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_code, write_date, update_date, recommandation, rank() over (order by recommandation desc) AS ranking, post_code FROM board LIMIT 3";
//
//			pstmt =  conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				int boardCode = rs.getInt(1);
//				String boardName = rs.getString(2);
//				String description = rs.getString(3) == null ? "" : rs.getString(3);
//				Timestamp createdDate = rs.getTimestamp(4);
//				String title = rs.getString(5);
//				String content = rs.getString(6);
//				String userId = userDao.findUserIdByCode(rs.getInt(7)+"");
//				Timestamp writeDate = rs.getTimestamp(8);
//				Timestamp updateDate = rs.getTimestamp(9);
//				int recommendation = rs.getInt(10);
//				int postCode = rs.getInt(12);
//
////				CommentResponseDto post = new CommentResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommendation, postCode);
////				postList.add(post);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return postList;
//	}
//
//	public List<CommentResponseDto> readAllBoard(){
//		List<CommentResponseDto> boardList = new ArrayList<CommentResponseDto>();
//
//		try {
//			conn = DBManager.getConnection();
//
//			String sql = "SELECT board_code, board_name, description, created_date FROM board_category";
//
//			pstmt =  conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				int boardCode = rs.getInt(1);
//				String boardName = rs.getString(2);
//				String description = rs.getString(3) == null ? "" : rs.getString(3);
//				Timestamp createdDate = rs.getTimestamp(4);
//
//				CommentResponseDto board = new CommentResponseDto(boardCode, boardName, description, createdDate);
//				boardList.add(board);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return boardList;
//	}
//
//	public List<CommentResponseDto> readAllPostByBoardCode(int code){
//		List<CommentResponseDto> postList = new ArrayList<CommentResponseDto>();
//
//		try {
//			conn = DBManager.getConnection();
//			UserDao userDao = UserDao.getInstance();
//
//			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_code, write_date, update_date, recommandation, post_code FROM board WHERE board_code=?";
//
//			pstmt =  conn.prepareStatement(sql);
//			pstmt.setInt(1, code);
//
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				int boardCode = rs.getInt(1);
//				String boardName = rs.getString(2);
//				String description = rs.getString(3) == null ? "" : rs.getString(3);
//				Timestamp createdDate = rs.getTimestamp(4);
//				String title = rs.getString(5);
//				String content = rs.getString(6);
//				String userId = userDao.findUserIdByCode(rs.getInt(7)+"");
//				Timestamp writeDate = rs.getTimestamp(8);
//				Timestamp updateDate = rs.getTimestamp(9);
//				int recommendation = rs.getInt(10);
//				int postCode = rs.getInt(11);
//
//				CommentResponseDto post = new CommentResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommendation, postCode);
//				postList.add(post);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return postList;
//	}
//
//	public CommentResponseDto readPostByBoardCodeAndPostCode(int boardCodeTemp, int postCodeTemp) {
//		CommentResponseDto post = null;
//
//		try {
//			conn = DBManager.getConnection();
//			UserDao userDao = UserDao.getInstance();
//
//			String sql = "SELECT title, content, user_code, write_date, update_date, recommandation, post_code, board_code FROM posts WHERE post_code=? AND board_code=?";
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, postCodeTemp);
//			pstmt.setInt(2, boardCodeTemp);
//
//			rs = pstmt.executeQuery();
//
//			if(rs.next()) {
//				String title = rs.getString(1);
//				String content = rs.getString(2);
//				String userId = userDao.findUserIdByCode(rs.getInt(3)+"");
//				Timestamp writeDate = rs.getTimestamp(4);
//				Timestamp updateDate = rs.getTimestamp(5);
//				int recommendation = rs.getInt(6);
//				int postCode = rs.getInt(7);
//				int boardCode = rs.getInt(8);
//
////				post = new CommentResponseDto(title, content, userId, writeDate, updateDate, recommendation, postCode, boardCode);
////				System.out.println("readPostByBoardCodeAndPostCode post : " + post);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, pstmt, rs);
//		}
//
//		return post;
//	}
//
//	public String saveImage(Part filePart) throws IOException {
//		// 파일명 추출
//		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//		// 저장할 경로 설정
//		Path targetPath = Paths.get(UPLOAD_DIR, fileName);
//		// 파일 저장
//		Files.copy(filePart.getInputStream(), targetPath);
//
//		return targetPath.toString();
//	}
}
