package job_posting.model;


import utill.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobPostingDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;



    private JobPostingDao() {
    }


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

            String sql = "SELECT posting_id , company_name,job_title, application_start, application_end, job_description , status FROM job_posting WHERE user_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int postingId = rs.getInt(1);
                String companyName = rs.getString(2);
                String job_title = rs.getString(3);
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
    public JobPostingResponseDto findJobPostingById(int postingId) {
        JobPostingResponseDto post = null;
        try {
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM job_posting WHERE posting_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postingId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                post = new JobPostingResponseDto();
                post.setPostingId(rs.getInt("posting_id"));
                post.setUserCode(rs.getInt("user_code"));
                post.setCompanyName(rs.getString("company_name"));
                post.setJobTitle(rs.getString("job_title"));
                post.setApplicationStart(rs.getDate("application_start"));
                post.setApplicationEnd(rs.getDate("application_end"));
                post.setDescription(rs.getString("job_description"));
                post.setStatus(rs.getString("status"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBManager.close(conn, pstmt,rs);
        }

        return post;
    }
    public JobPostingResponseDto updateJobPosting(JobPostingRequestDto jobPostingRequestDto){
        JobPostingResponseDto post = null;
        try{
            String sql = "UPDATE job_posting SET company_name = ?, job_title = ?, application_start = ?, application_end = ?, job_description = ?, status = ? WHERE posting_id = ?";
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,jobPostingRequestDto.getCompanyName());
            pstmt.setString(2,jobPostingRequestDto.getJobTitle());
            pstmt.setDate(3,jobPostingRequestDto.getApplicationStart());
            pstmt.setDate(4,jobPostingRequestDto.getApplicationEnd());
            pstmt.setString(5,jobPostingRequestDto.getDescription());
            pstmt.setString(6,jobPostingRequestDto.getStatus());
            pstmt.setInt(7,jobPostingRequestDto.getPostingId());
            pstmt.executeUpdate();
            post = findJobPostingById(jobPostingRequestDto.getPostingId());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt);
        }
        return null;
    }
    public boolean deleteJobPosting(JobPostingRequestDto jobPostingRequestDto){
        try {
            conn = DBManager.getConnection();
            String sql = "DELETE FROM job_posting WHERE posting_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,jobPostingRequestDto.getPostingId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return false;
    }
}
