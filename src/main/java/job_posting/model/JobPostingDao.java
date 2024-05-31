package job_posting.model;


import utill.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobPostingDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // JobPostingDao 객체를 단일 인스턴스로 만들기 위해
    // Singleton Pattern 적용

    // 1. 생성자를 private으로
    private JobPostingDao() {
    }

    // 2. 단일 인스턴스를 생성 (클래스 내부에서)
    private static JobPostingDao instance = new JobPostingDao();

    public List<JobPostingResponseDto> findMyJobPostingAll(String userId) {

        List<JobPostingResponseDto> list = new ArrayList<JobPostingResponseDto>();

        try {
            conn = DBManager.getConnection();

            // 쿼리할 준비
            String sql = "SELECT posting_id, user_code , company_name,job_title, application_start, application_end, job_description , status FROM job_posting";
            pstmt = conn.prepareStatement(sql);

            // 쿼리 실행
            rs = pstmt.executeQuery();

            // 튜플 읽기
            while (rs.next()) {
                // database의 column index는 1부터 시작!
                int postingId = rs.getInt(1);
                int userCode = rs.getInt(2);
                String companyName = rs.getString(3);
                String job_title = rs.getString(2);
                Date applicationStart = rs.getDate(4);
                Date applicationEnd = rs.getDate(5);
                String job_description = rs.getString(6);
                String status = rs.getString(7);

                JobPostingResponseDto jobPost = new JobPostingResponseDto(postingId, userCode, companyName, job_title, applicationStart, applicationEnd, job_description,status);
                list.add(jobPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt,rs);
        }
        return list;
    }
}
