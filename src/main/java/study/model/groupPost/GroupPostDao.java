package study.model.groupPost;

import study.model.studyGroup.StudyGroupRequestDto;
import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupPostDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private GroupPostDao() {
    }

    private static GroupPostDao instance = new GroupPostDao();

    public static GroupPostDao getInstance() {
        return instance;
    }

    public List<GroupPostResponseDto> getGroupPostsByGroupCode(String groupCode) {
        List<GroupPostResponseDto> list = new ArrayList<GroupPostResponseDto>();
        ;

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT post_code,user_code,title,content,update_date,group_code,(SELECT id FROM users u WHERE u.user_code = gp.user_code) FROM group_posts gp WHERE group_code =? ORDER BY write_date DESC LIMIT 5";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, groupCode);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String postCode = rs.getString(1);
                String userCode = rs.getString(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                String updateDate = rs.getString(5);
                String userId = rs.getString(7);
                GroupPostResponseDto gp = new GroupPostResponseDto(postCode, groupCode, userCode, title, content, updateDate, userId);

                list.add(gp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return list;
    }

    public boolean createGroupPost(GroupPostRequestDto groupPostRequestDto) {
        boolean isValid = false;
        String groupCode = groupPostRequestDto.getGroupCode();
        String userCode = groupPostRequestDto.getUserCode();
        String title = groupPostRequestDto.getTitle();
        String content = groupPostRequestDto.getContent();

        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO group_posts (group_code,user_code,title,content) VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, groupCode);
            pstmt.setString(2, userCode);
            pstmt.setString(3, title);
            pstmt.setString(4, content);

            isValid = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return isValid;
    }

    public GroupPostResponseDto getGroupPostDetail(GroupPostRequestDto groupPostRequestDto) {
        GroupPostResponseDto groupPostResponseDto = null;

        try {
            String postCode = groupPostRequestDto.getPostCode();
            conn = DBManager.getConnection();
            String sql = "SELECT gp.post_code, gp.group_code, gp.user_code,gp.title, gp.content, gp.update_date, gp.recommendation, u.id FROM group_posts gp JOIN users u ON gp.user_code = u.user_code WHERE gp.post_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, postCode);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                String groupCode = rs.getString(2);
                String userCode = rs.getString(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String updateDate = rs.getString(6);
                int recommendation = rs.getInt(7);
                String userId = rs.getString(8);

                groupPostResponseDto = new GroupPostResponseDto(recommendation, userId, updateDate, content, title, groupCode, userCode, postCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return groupPostResponseDto;
    }

    public boolean updateGroupPost(GroupPostRequestDto groupPostRequestDto) {
        boolean isValid = false;

        try {
            String postCode = groupPostRequestDto.getPostCode();
            String title = groupPostRequestDto.getTitle();
            String content = groupPostRequestDto.getContent();

            conn = DBManager.getConnection();
            String sql = "UPDATE group_posts SET title = ?, content = ? WHERE post_code = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, postCode);

            isValid = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return isValid;
    }

    public boolean deleteGroupPost(GroupPostRequestDto groupPostRequestDto) {
        boolean isValid = false;

        try {
            String postCode = groupPostRequestDto.getPostCode();

            conn = DBManager.getConnection();
            String sql = "DELETE FROM group_posts WHERE post_code = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, postCode);

            isValid = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return isValid;
    }

    public int getGroupPostsCount(StudyGroupRequestDto studyGroupRequestDto) {
        int count = 0;

        try {
            String groupCode = studyGroupRequestDto.getGroupCode();
            conn = DBManager.getConnection();
            String sql = "SELECT COUNT(*) FROM group_posts WHERE group_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, groupCode);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return count;
    }

    public List<GroupPostResponseDto> getGroupPosts(String groupCode, String offset) {
        List<GroupPostResponseDto> list = new ArrayList<>();

        try {
            conn = DBManager.getConnection();
            String sql = "SELECT post_code,user_code,title,content,update_date,group_code,(SELECT id FROM users u WHERE u.user_code = gp.user_code) FROM group_posts gp WHERE group_code =? ORDER BY write_date DESC LIMIT 5 OFFSET ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, groupCode);
            pstmt.setInt(2, Integer.parseInt(offset));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String postCode = rs.getString(1);
                String userCode = rs.getString(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                String updateDate = rs.getString(5);
                String userId = rs.getString(7);
                GroupPostResponseDto gp = new GroupPostResponseDto(postCode, groupCode, userCode, title, content, updateDate, userId);

                list.add(gp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return list;
    }
}
