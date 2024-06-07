package user.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;


import utill.DBManager;
import utill.PasswordCrypto;

public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;


	private UserDao() {
	}


	private static UserDao instance = new UserDao();


	public static UserDao getInstance() {
		return instance;
	}

	public List<UserResponseDto> findUserAll() {

		List<UserResponseDto> list = new ArrayList<>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT user_code, id , name, resident_number, phone, admin , email FROM users";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int userCode = rs.getInt(1);
				String id = rs.getString(2);
				String name = rs.getString(3);
				String resident_number = rs.getString(4);
				String phone = rs.getString(5);
				boolean admin = rs.getBoolean(6);
				String email = rs.getString(7);

				UserResponseDto user = new UserResponseDto(userCode, id, name, resident_number, phone, admin, email);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return list;
	}
	public boolean checkIdAvailability(String id){
		conn = DBManager.getConnection();
        try {
		String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
            rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count == 0;
			}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
			DBManager.close(conn, pstmt,rs);
		}
        return false;
	}
	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT user_code, id , name, resident_number ,phone, admin , email, password FROM users WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int userCode = rs.getInt(1);
				String name = rs.getString(3);
				String resident_number = rs.getString(4);
				String phone = rs.getString(5);
				boolean admin = rs.getBoolean(6);
				String email = rs.getString(7);
				String encyptedPassword = rs.getString(8);
				if (PasswordCrypto.decrypt(password, encyptedPassword)) {
					user = new UserResponseDto(userCode, id, password ,name, resident_number, phone, admin, email);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt,rs);
		}
		return user;
	}
	public UserResponseDto createUser(UserRequestDto userDto) {
		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO users(id, password, email, name, resident_number,user_age,phone, admin) VALUES(?, ?, ?, ?, ?, ?,? ,?)";

			pstmt = conn.prepareStatement(sql);


			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userDto.getPassword()));
			String email = userDto.getEmail().equals("") ? null : userDto.getEmail();
			pstmt.setString(3, email);
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getResident_number());
			pstmt.setInt(6, userDto.getAge());
			pstmt.setString(7, userDto.getPhone());
			pstmt.setBoolean(8, userDto.isAdmin());
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


			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	public UserResponseDto updateUserPassword(String userId, String newPassword) {
		UserResponseDto user = null;

		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE users SET password=?   WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, userId);
			pstmt.execute();

			user = findUserByIdAndPassword(userId, newPassword);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	public UserResponseDto updateUserField(String userId, String password, String field, String value) {

		UserResponseDto user = null;
		String sql = "";
		if (field.equals("email")) {
			sql = "UPDATE users SET email=? WHERE id=?";
		} else if (field.equals("phone")) {
			sql = "UPDATE users SET phone=? WHERE id=?";
		}
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, userId);
			pstmt.execute();
			user = findUserByIdAndPassword(userId,password);
		} catch (SQLException e) {
			e.printStackTrace();
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

	public UserResponseDto getUserById(String id) {
		UserResponseDto user = null;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT user_code, id ,password, name, resident_number, phone, admin , email FROM users WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int userCode = rs.getInt(1);
				String password = rs.getString(3);
                String encyptedPassword = rs.getString(2);
                String name = rs.getString(4);
                String resident_number = rs.getString(5);
                String phone = rs.getString(6);
                boolean admin = rs.getBoolean(7);
                String email = rs.getString(8);
				if (PasswordCrypto.decrypt(password, encyptedPassword))
					user = new UserResponseDto(userCode, id, name, resident_number, phone, admin, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public int findUserCodeById(String id) {
		int userCode = -1;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT user_code FROM users WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				userCode = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return userCode;
	}

	public String findUserIdByCode(int userCode) {
		String userId = "";

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id FROM users WHERE user_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userCode);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				userId = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return userId;
	}
}
