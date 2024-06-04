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
	
	public boolean createComment(CommentRequestDto commentRequestDto) {
		boolean isSuccess = false;

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO comment(user_code, content, post_code) VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, commentRequestDto.getUserCode());
			pstmt.setString(2, commentRequestDto.getContent());
			pstmt.setInt(3, commentRequestDto.getPostCode());

			pstmt.execute();
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isSuccess;
	}

	public boolean updateComment(CommentRequestDto commentRequestDto) {
		boolean isSuccess = false;

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE comment SET content = ? WHERE comment_code = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, commentRequestDto.getContent());
			pstmt.setInt(2, commentRequestDto.getCommentCode());

			pstmt.execute();
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isSuccess;
	}

	public List<CommentResponseDto> readAllCommentByPostCode(int reqPostCode) {
		List<CommentResponseDto> commentList = new ArrayList<>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM comment_res WHERE post_code=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reqPostCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int commentCode = rs.getInt(1);
				String userId = rs.getString(3);
				String content = rs.getString(4);
				int postCode = rs.getInt(5);
				Timestamp writeDate = rs.getTimestamp(6);
				Timestamp updateDate = rs.getTimestamp(7);

				CommentResponseDto comment = new CommentResponseDto(commentCode, userId, postCode, content, writeDate, updateDate);
				commentList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return commentList;
	}

	public boolean deleteComment(int reqCommentCode) {
		boolean isSuccess = false;

		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM comment WHERE comment_code=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reqCommentCode);

			pstmt.execute();
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isSuccess;
	}

}
