package study.model.groupAwaiter;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupAwaiterDao {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    private GroupAwaiterDao (){

    }
    private static GroupAwaiterDao instance = new GroupAwaiterDao();

    public static GroupAwaiterDao getInstance() {
        return instance;
    }

    public List<GroupAwaiterResponseDto> getStudyAwaiters(GroupAwaiterRequestDto gaReqDto) {
        List<GroupAwaiterResponseDto> list = null;
        try{
            conn = DBManager.getConnection();
            String sql="SELECT user_code, group_code ,comment,(SELECT id FROM users u WHERE u.user_code = gm.user_code) FROM group_awaiter gm WHERE group_code = ? ORDER BY send_date";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gaReqDto.getGroupCode());
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                String userCode = rs.getString(1);
                String groupCode = rs.getString(2);
                String comment = rs.getString(3);
                String userId = rs.getString(4);

                GroupAwaiterResponseDto gaDto = new GroupAwaiterResponseDto(userCode, groupCode, comment, userId);

                list.add(gaDto);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBManager.close(conn,pstmt,rs);
        }
        return list;
    }
}
