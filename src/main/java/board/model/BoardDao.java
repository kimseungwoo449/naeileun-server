package board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import user.model.UserDao;
import utill.DBManager;
import utill.ImageHandler;

public class BoardDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static final String UPLOAD_DIR = "src/main/java/board/image";

	private BoardDao() {
		
	}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	public BoardResponseDto createPost(String reqTitle, String reqContent, String reqUserId, String reqBoardCode, String reqImagePath) {
		BoardResponseDto responseDto = null;

		UserDao userDao = UserDao.getInstance();
		int userCode = userDao.findUserCodeById(reqUserId);
		System.out.println("userCode : " + userCode);

		boolean isSuccess = false;
		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO posts(title, content, user_code, board_code, image_path) VALUES(?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, reqTitle);
			pstmt.setString(2, reqContent);
			pstmt.setInt(3, userCode);
			pstmt.setInt(4, Integer.parseInt(reqBoardCode));
			pstmt.setString(5, reqImagePath);

			pstmt.execute();
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);

			if(isSuccess) {
				responseDto = findBoardByUserCodeLatest(userCode);
			}
		}

		return responseDto;
	}

	private BoardResponseDto findBoardByUserCodeLatest(int userCode) {
		BoardResponseDto responseDto = null;

		String sql = "SELECT * FROM post_res WHERE user_code=? ORDER BY write_date DESC LIMIT 1";

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				String userId = rs.getString(4);
				Timestamp writeDate = rs.getTimestamp(5);
				Timestamp updateDate = rs.getTimestamp(6);
				int recommendation = rs.getInt(7);
				int postCode = rs.getInt(8);
				int boardCode = rs.getInt(9);
				String imagePath = rs.getString(10);

				responseDto = new BoardResponseDto(title, content, userId, writeDate, updateDate, recommendation, postCode, boardCode, imagePath);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return responseDto;
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

			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_id, write_date, update_date, recommandation, rank() over (order by recommandation desc) AS ranking, post_code FROM board LIMIT 3";
			
			pstmt =  conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardCode = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3) == null ? "" : rs.getString(3);
				Timestamp createdDate = rs.getTimestamp(4);
				String title = rs.getString(5);
				String content = rs.getString(6);
				String userId = rs.getString(7);
				Timestamp writeDate = rs.getTimestamp(8);
				Timestamp updateDate = rs.getTimestamp(9);
				int recommendation = rs.getInt(10);
				int postCode = rs.getInt(12);
				
				BoardResponseDto post = new BoardResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommendation, postCode);
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
	
	public List<BoardResponseDto> readAllPostByBoardCode(int page, String search, int code){
		List<BoardResponseDto> postList = new ArrayList<BoardResponseDto>();

		int offset = (page - 1) * 10;
		if(search == null || search.equals(""))
			search = "%";

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT board_code, board_name, description, created_date, title, content, user_id, write_date, update_date, recommandation, post_code FROM board WHERE board_code=? AND title LIKE ? LIMIT 10 OFFSET ?";
			
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, offset);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardCode = rs.getInt(1);
				String boardName = rs.getString(2);
				String description = rs.getString(3) == null ? "" : rs.getString(3);
				Timestamp createdDate = rs.getTimestamp(4);
				String title = rs.getString(5);
				String content = rs.getString(6);
				String userId = rs.getString(7);
				Timestamp writeDate = rs.getTimestamp(8);
				Timestamp updateDate = rs.getTimestamp(9);
				int recommendation = rs.getInt(10);
				int postCode = rs.getInt(11);
				
				BoardResponseDto post = new BoardResponseDto(boardCode, boardName, description, createdDate, title, content, userId, writeDate, updateDate, recommendation, postCode);
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return postList;
	}

	public int countPostByBoardCode(int boardCode) {
		int count = -1;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT count(*) FROM board WHERE board_code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	public BoardResponseDto readPostByBoardCodeAndPostCode(int boardCodeTemp, int postCodeTemp) {
		BoardResponseDto post = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT title, content, user_id, write_date, update_date, recommandation, post_code, board_code, image_path FROM post_res WHERE post_code=? AND board_code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postCodeTemp);
			pstmt.setInt(2, boardCodeTemp);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				String userId = rs.getString(3);
				Timestamp writeDate = rs.getTimestamp(4);
				Timestamp updateDate = rs.getTimestamp(5);
				int recommendation = rs.getInt(6);
				int postCode = rs.getInt(7);
				int boardCode = rs.getInt(8);
				String imagePath = rs.getString(9) == null ? "" : rs.getString(9);

				post = new BoardResponseDto(title, content, userId, writeDate, updateDate, recommendation, postCode, boardCode, imagePath);
				System.out.println("readPostByBoardCodeAndPostCode post : " + post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return post;
	}

	public boolean deletePostByPostCode(int boardCode, String userId, int postCode) {
		boolean isDelete = false;

		BoardResponseDto post = readPostByBoardCodeAndPostCode(boardCode, postCode);
		if(post.getUserId().equals(userId)){
			try {
				conn = DBManager.getConnection();

				String sql = "DELETE FROM posts WHERE post_code = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, postCode);

				pstmt.execute();
				isDelete = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}

		return isDelete;
	}

	public boolean deleteImage(String imageUrl) {
		System.out.println("이미지 삭제하기");
		boolean isDelete = false;

		if(!imageUrl.equals("")) {
			isDelete = ImageHandler.deleteImage(imageUrl);
			System.out.println("isDelete : " + isDelete);
		}else {
			System.out.println("이미지 없음");
		}

		return isDelete;
	}

	public BoardResponseDto UpdatePost(String reqTitle, String reqContent, String reqUserId, int reqPostCode, String reqImagePath) {
		BoardResponseDto responseDto = null;

		UserDao userDao = UserDao.getInstance();
		int userCode = userDao.findUserCodeById(reqUserId);
		System.out.println("UpdatePost userCode : " + userCode);

		boolean isSuccess = false;
		try {
			conn = DBManager.getConnection();

			String sql = "";
			if(reqImagePath.equals("")) {
				sql = "UPDATE posts SET title = ?, content=? WHERE post_code=?";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, reqTitle);
				pstmt.setString(2, reqContent);
				pstmt.setInt(3, reqPostCode);
			}
			else if(!reqImagePath.equals("")) {
				sql = "UPDATE posts SET title = ?, content=?, image_path=? WHERE post_code=?";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, reqTitle);
				pstmt.setString(2, reqContent);
				pstmt.setString(3, reqImagePath);
				pstmt.setInt(4, reqPostCode);
			}

			pstmt.execute();
			isSuccess = true;
			System.out.println("UpdatePost isSuccess : " + isSuccess);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);

			if(isSuccess) {
				System.out.println("reqPostCode : " + reqPostCode);
				responseDto = findPostByPostCode(reqPostCode);
			}
		}

		return responseDto;
	}

	private BoardResponseDto findPostByPostCode(int resPostCode) {
		BoardResponseDto responseDto = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM post_res WHERE post_code =?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, resPostCode);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				String userId = rs.getString(4);
				Timestamp writeDate = rs.getTimestamp(5);
				Timestamp updateDate = rs.getTimestamp(6);
				int recommendation = rs.getInt(7);
				int postCode = rs.getInt(8);
				int boardCode = rs.getInt(9);
				String imagePath = rs.getString(10);

				responseDto = new BoardResponseDto(title, content, userId, writeDate, updateDate, recommendation, postCode, boardCode, imagePath);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return responseDto;
	}

	public boolean deletePostByUserCode(int userCode) {
		boolean isDelete = false;

		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM posts WHERE user_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);

			pstmt.execute();
			isDelete = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDelete;
	}
}
