package resume.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import utill.DBManager;

public class ResumeDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// UserDao 객체를 단일 인스턴스로 만들기 위해
	// Singleton Pattern 적용

	// 1. 생성자를 private으로
	private ResumeDao() {
	}

	// 2. 단일 인스턴스를 생성 (클래스 내부에서)
	private static ResumeDao instance = new ResumeDao();

	// 3. 단일 인스턴스에 대한 getter
	public static ResumeDao getInstance() {
		return instance;
	}


	public ResumeResponseDto createResume(ResumeRequestDto dto) {
		ResumeResponseDto response = null;

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO resume(user_code,name,title,user_age,academic_career,career,skill,certificate,language,award) VALUES(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			// sql 구문에 맵핑할 값 설정
			pstmt.setInt(1, dto.getUserCode());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTitle());
			pstmt.setInt(4, dto.getUserAge());
			pstmt.setString(5, dto.getAcademicCareer());
			pstmt.setString(6, dto.getCareer());
			pstmt.setString(7, dto.getSkill());
			pstmt.setString(8, dto.getCertificate());
			pstmt.setString(9, dto.getLanguage());
			pstmt.setString(10, dto.getAward());

			pstmt.execute();

			response = findResumeByResumeCode(lastResumeCode());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return response;
	}

	public ResumeResponseDto updateResume(ResumeRequestDto dto) {
		ResumeResponseDto response = null;

		if (dto.getResumeCode() == 0 || dto.getUserCode() == 0 || dto.getName() == null || dto.getTitle() == null || dto.getUserAge() == 0 || dto.getAcademicCareer() == null) {
			return response;
		}

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE resume SET title=?, academic_career=?, career=?, skill=?, certificate=?, language=?, award=?, update_date=NOW() WHERE resume_code = ?";

			pstmt = conn.prepareStatement(sql);

			// sql 구문에 맵핑할 값 설정
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAcademicCareer());
			pstmt.setString(3, dto.getCareer());
			pstmt.setString(4, dto.getSkill());
			pstmt.setString(5, dto.getCertificate());
			pstmt.setString(6, dto.getLanguage());
			pstmt.setString(7, dto.getAward());
			pstmt.setInt(8, dto.getResumeCode());

			pstmt.execute();

			response = findResumeByResumeCode(dto.getResumeCode());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return response;
	}

	public ResumeResponseDto getResume(ResumeRequestDto dto) {
		ResumeResponseDto response = null;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM resume WHERE resume_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getResumeCode());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				response = new ResumeResponseDto();
				response.setResumeCode(rs.getInt(1));
				response.setUserCode(rs.getInt(2));
				response.setName(rs.getString(3));
				response.setTitle(rs.getString(4));
				response.setUserAge(rs.getInt(5));
				response.setAcademicCareer(rs.getString(6));
				response.setCareer(rs.getString(7));
				response.setSkill(rs.getString(8));
				response.setCertificate(rs.getString(9));
				response.setLanguage(rs.getString(10));
				response.setAward(rs.getString(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		if (response.getName() == null) {
			response = null;
		}

		return response;
	}

	public boolean deleteResume(ResumeRequestDto dto) {
		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM resume WHERE resume_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getResumeCode());

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		if(findResumeByResumeCode(dto.getResumeCode())==null) {
			return true;
		}

		return false;
	}

	public boolean deleteAllResume(ResumeRequestDto dto) {
		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM resume WHERE user_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserCode());

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		if(getAllResume(dto)==null) {
			System.out.println(getAllResume(dto));
			return true;
		}

		return false;
	}


	private int lastResumeCode() {
		int lastResumeCode = -1;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT MAX(resume_code) FROM resume";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lastResumeCode = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return lastResumeCode;
	}

	public List<ResumeResponseDto> getAllResume(ResumeRequestDto dto){
		List<ResumeResponseDto> response = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM resume WHERE `user_code` = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserCode());
			rs = pstmt.executeQuery();

			response = new ArrayList<ResumeResponseDto>();

			while (rs.next()) {
				int resumeCode = rs.getInt(1);
				String userName = rs.getString(3);
				String title = rs.getString(4);
				int userAge = rs.getInt(5);
				String academicCareer = rs.getString(6);
				String career = rs.getString(7);
				String skill = rs.getString(8);
				String certificate = rs.getString(9);
				String language = rs.getString(10);
				String award = rs.getString(11);
				Timestamp writeDate = rs.getTimestamp(12);
				Timestamp updateDate = rs.getTimestamp(13);

				ResumeResponseDto resume = new ResumeResponseDto(resumeCode, dto.getUserCode(), userName, title, userAge, academicCareer, career, skill, certificate, language, award, writeDate, updateDate);
				response.add(resume);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(response.size() == 0) {
			response = null;
		}

		return response;
	}

	public ResumeResponseDto findResumeByResumeCode(int resumeCode) {
		ResumeResponseDto resume = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM resume WHERE `resume_code` =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resumeCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int userCode = rs.getInt(2);
				String userName = rs.getString(3);
				String title = rs.getString(4);
				int userAge = rs.getInt(5);
				String academicCareer = rs.getString(6);
				String career = rs.getString(7);
				String skill = rs.getString(8);
				String certificate = rs.getString(9);
				String language = rs.getString(10);
				String award = rs.getString(11);
				Timestamp writeDate = rs.getTimestamp(12);
				Timestamp updateDate = rs.getTimestamp(13);

				resume = new ResumeResponseDto(resumeCode, userCode, userName, title, userAge, academicCareer, career, skill, certificate, language, award, writeDate, updateDate);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return resume;
	}
}
