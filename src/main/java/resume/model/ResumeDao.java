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

	private ResumeDao() {
	}


	private static ResumeDao instance = new ResumeDao();

	public static ResumeDao getInstance() {
		return instance;
	}


	public ResumeResponseDto createResume(ResumeRequestDto dto) {
		ResumeResponseDto response = null;

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO resume(user_code,name,title,user_age,academic_career,career,skill,certificate,language,award,phone,expected_salary,expected_region,is_newbie) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);


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
			pstmt.setString(11, dto.getPhone());
			pstmt.setString(12, dto.getExpectedSalary());
			pstmt.setString(13, dto.getExpectedRegion());
			pstmt.setBoolean(14, dto.isNewbie());
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		response = findResumeByResumeCode(lastResumeCode());

		return response;
	}

	public ResumeResponseDto updateResume(ResumeRequestDto dto) {
		ResumeResponseDto response = null;

		if (dto.getResumeCode() == 0 || dto.getUserCode() == 0 || dto.getName() == null ||
				dto.getTitle() == null || dto.getUserAge() == 0 || dto.getAcademicCareer() == null) {
			return response;
		}

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE resume SET title=?, academic_career=?, career=?, skill=?, certificate=?, language=?, award=?, expected_salary=?,expected_region=?,is_newbie=?,phone=?,update_date=NOW() WHERE resume_code = ?";

			pstmt = conn.prepareStatement(sql);


			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAcademicCareer());
			pstmt.setString(3, dto.getCareer());
			pstmt.setString(4, dto.getSkill());
			pstmt.setString(5, dto.getCertificate());
			pstmt.setString(6, dto.getLanguage());
			pstmt.setString(7, dto.getAward());
			pstmt.setString(8, dto.getExpectedSalary());
			pstmt.setString(9, dto.getExpectedRegion());
			pstmt.setBoolean(10, dto.isNewbie());
			pstmt.setString(11, dto.getPhone());
			pstmt.setInt(12, dto.getResumeCode());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		response = findResumeByResumeCode(dto.getResumeCode());

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
				response.setPhone(rs.getString(14));
				response.setExpectedSalary(rs.getString(15));
				response.setExpectedRegion(rs.getString(16));
				response.setNewbie(rs.getBoolean(17));
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
				String phone = rs.getString(14);
				String expectedSalary = rs.getString(15);
				String expectedRegion = rs.getString(16);
				boolean newbie = rs.getBoolean(17);
				ResumeResponseDto resume = new ResumeResponseDto(resumeCode, dto.getUserCode(), userName,
						title, userAge, academicCareer, career, skill, certificate, language, award, writeDate,
						updateDate,phone, expectedSalary, expectedRegion, newbie);
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
				String phone = rs.getString(14);
				String expectedSalary = rs.getString(15);
				String expectedRegion = rs.getString(16);
				boolean isNewbie = rs.getBoolean(17);
				resume = new ResumeResponseDto(resumeCode,userCode,userName,title,userAge,academicCareer,career,skill,certificate,language,award,writeDate,updateDate,phone,expectedSalary,expectedRegion,isNewbie);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return resume;
	}
}
