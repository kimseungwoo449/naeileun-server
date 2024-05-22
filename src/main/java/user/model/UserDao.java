package user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import utill.DBManager;
import utill.PasswordCrypto;

public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// UserDao 객체를 단일 인스턴스로 만들기 위해
	// Singleton Pattern 적용

	// 1. 생성자를 private으로
	private UserDao() {
	}

	// 2. 단일 인스턴스를 생성 (클래스 내부에서)
	private static UserDao instance = new UserDao();

	// 3. 단일 인스턴스에 대한 getter
	public static UserDao getInstance() {
		return instance;
	}

	public List<UserResponseDto> findUserAll() {

		List<UserResponseDto> list = new ArrayList<UserResponseDto>();

		try {
			conn = DBManager.getConnection();

			// 쿼리할 준비
			String sql = "SELECT id , name, resident_number, phone, admin , email FROM users";
			pstmt = conn.prepareStatement(sql);

			// 쿼리 실행
			rs = pstmt.executeQuery();

			// 튜플 읽기
			while (rs.next()) {
				// database의 column index는 1부터 시작!
				String id = rs.getString(1);
				String name = rs.getString(2);
				String resident_number = rs.getString(3);
				String phone = rs.getString(4);
				boolean admin = rs.getBoolean(5);
				String email = rs.getString(6);

				UserResponseDto user = new UserResponseDto(id, name, resident_number, phone, admin, email);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT id , name, resident_number, phone, admin , email, password FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				String name = rs.getString(2);
				String resident_number = rs.getString(3);
				String phone = rs.getString(4);
				boolean admin = rs.getBoolean(5);
				String email = rs.getString(6);
				String encyptedPassword = rs.getString(7);

				if (PasswordCrypto.decrypt(password, encyptedPassword))
					user = new UserResponseDto(id, name, resident_number, phone, admin, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

//	public boolean userExists(UserRequestDto userDto) {
//		return findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) != null;
//	}
//
//	public boolean userExists(String id) {
//		return findUserById(id) != null;
//	}

	public UserResponseDto createUser(UserRequestDto userDto) {
		// sql 구문을 쿼리하고
		// 성공을 했다면 -> UserResponseDto 객체 생성하여
		// 반환

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO users(id, password, email, name, resident_number,phone, admin) VALUES(?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			// sql 구문에 맵핑할 값 설정
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userDto.getPassword()));
			String email = userDto.getEmail().equals("") ? null : userDto.getEmail();
			pstmt.setString(3, email);
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getResident_number());
			pstmt.setString(6, userDto.getPhone());
			pstmt.setBoolean(7, userDto.isAdmin());

			pstmt.execute();

			return findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}

	public UserResponseDto updateUserPassword(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;

		if (newPassword == null || newPassword.equals("")) {
			return user;
		}

		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return user;

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE users SET password=?   WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, userDto.getId());
			pstmt.execute();

			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	public UserResponseDto updateUserEmail(UserRequestDto userDto) {
		UserResponseDto user = null;

		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return user;

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE users SET email=?  WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getEmail());
			pstmt.setString(2, userDto.getId());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	public UserResponseDto updateUserPhone(UserRequestDto userDto) {
		UserResponseDto user = null;

		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return user;

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE users SET phone=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getPhone());
			pstmt.setString(2, userDto.getId());

			pstmt.execute();

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	public boolean deleteUser(UserRequestDto userDto) {
		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return false;

		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM users WHERE id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getId());
			pstmt.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return false;
	}

	private User findUserById(String id) {
		User user = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT id , name, resident_number, phone, admin , email, reg_Date FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString(2);
				String resident_number = rs.getString(3);
				String phone = rs.getString(4);
				boolean admin = rs.getBoolean(5);
				String email = rs.getString(6);
				Timestamp regDate = rs.getTimestamp(7);

				user = new User(id, name, resident_number, phone, admin, email, regDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}
	public String findUserCodeById(String id) {
		String userCode = "";
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT code FROM users WHERE id = ?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			if(rs.next()) {
				 userCode = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return userCode;
	}
}
