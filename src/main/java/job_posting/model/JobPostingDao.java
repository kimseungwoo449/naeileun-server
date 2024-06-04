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
    public static JobPostingDao getInstance() {
        return instance;
    }

    public JobPostingResponseDto createJobPosting(JobPostingRequestDto jobPost){
        JobPostingResponseDto dto = null;
        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO job_posting(user_code , company_name,job_title, application_start, application_end, job_description) VALUES(?,?,?,?,?,?)";;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,jobPost.getUserCode());
            pstmt.setString(2,jobPost.getCompanyName());
            pstmt.setString(3,jobPost.getJobTitle());
            pstmt.setDate(4,jobPost.getApplicationStart());
            pstmt.setDate(5,jobPost.getApplicationEnd());
            pstmt.setString(6,jobPost.getDescription());

            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBManager.close(conn, pstmt);
        }
        return dto;
    }

    public List<JobPostingResponseDto> findMyJobPostingAll(int userCode) {

        List<JobPostingResponseDto> list = new ArrayList<JobPostingResponseDto>();

        try {
            conn = DBManager.getConnection();

            // 쿼리할 준비
            String sql = "SELECT posting_id , company_name,job_title, application_start, application_end, job_description , status FROM job_posting WHERE user_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userCode);
            // 쿼리 실행
            rs = pstmt.executeQuery();
            System.out.println("1");
            // 튜플 읽기
            while (rs.next()) {
                // database의 column index는 1부터 시작!
                int postingId = rs.getInt(1);
                String companyName = rs.getString(2);
                String job_title = rs.getString(3);
                Date applicationStart = rs.getDate(4);
                Date applicationEnd = rs.getDate(5);
                String job_description = rs.getString(6);
                String status = rs.getString(7);

                JobPostingResponseDto jobPost = new JobPostingResponseDto(postingId, userCode, companyName, job_title, applicationStart, applicationEnd, job_description,status);
                list.add(jobPost);
                System.out.println("내자료 찾기성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("내자료 찾기실패");
        }finally {
            DBManager.close(conn, pstmt,rs);
        }
        return list;
    }
}
