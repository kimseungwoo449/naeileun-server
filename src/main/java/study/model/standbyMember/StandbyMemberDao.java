package study.model.standbyMember;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandbyMemberDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private StandbyMemberDao() {

    }

    private static StandbyMemberDao instance = new StandbyMemberDao();

    public static StandbyMemberDao getInstance() {
        return instance;
    }

    public List<StandbyMemberResponseDto> getStudyStandbyMembers(StandbyMemberRequestDto gaReqDto) {
        List<StandbyMemberResponseDto> list = null;
        try {
            conn = DBManager.getConnection();
            String sql = "SELECT user_code, group_code ,comment,(SELECT id FROM users u WHERE u.user_code = sm.user_code) FROM standby_member sm WHERE group_code = ? ORDER BY send_date";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gaReqDto.getGroupCode());
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String userCode = rs.getString(1);
                String groupCode = rs.getString(2);
                String comment = rs.getString(3);
                String userId = rs.getString(4);

                StandbyMemberResponseDto gaDto = new StandbyMemberResponseDto(userCode, groupCode, comment, userId);

                list.add(gaDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public boolean checkStandbyMember(StandbyMemberRequestDto gaReqDto) {
        boolean isValid = false;

        try {
            String groupCode = gaReqDto.getGroupCode();
            String userCode = gaReqDto.getUserCode();
            conn = DBManager.getConnection();
            String sql = "SELECT EXISTS (SELECT user_code FROM standby_member WHERE group_code = ? AND user_code = ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, groupCode);
            pstmt.setString(2, userCode);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                int exist = rs.getInt(1);
                if (exist == 1) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return isValid;
    }

    public boolean addStandbyMember(StandbyMemberRequestDto gaReqDto) {
        boolean isValid = false;
        try {
            String groupCode = gaReqDto.getGroupCode();
            String userCode = gaReqDto.getUserCode();
            String comment = gaReqDto.getComment();

            conn = DBManager.getConnection();
            String sql = "INSERT INTO standby_member (group_code,user_code,comment) VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, groupCode);
            pstmt.setString(2, userCode);
            pstmt.setString(3, comment);

            pstmt.execute();
            isValid = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return isValid;
    }

    public boolean deleteStandbyMember(StandbyMemberRequestDto gaReqDto) {
        boolean isValid = false;
        try {
            String groupCode = gaReqDto.getGroupCode();
            String userCode = gaReqDto.getUserCode();

            conn = DBManager.getConnection();
            String sql = "DELETE FROM standby_member WHERE group_code = ? AND user_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, groupCode);
            pstmt.setString(2, userCode);

            pstmt.execute();

            isValid = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return isValid;
    }
}
