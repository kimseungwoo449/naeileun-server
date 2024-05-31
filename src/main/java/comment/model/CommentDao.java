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
	
	public Boolean createComment(CommentRequestDto commentRequestDto) {
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

}
