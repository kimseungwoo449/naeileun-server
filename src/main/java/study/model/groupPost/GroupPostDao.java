package study.model.groupPost;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupPostDao {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

	private GroupPostDao(){}

    private static GroupPostDao instance = new GroupPostDao();

    public static GroupPostDao getInstance() {
        return instance;
    }

    public List<GroupPostResponseDto> getGroupPostsByGroupCode(String groupCode){
        List<GroupPostResponseDto> list = null;

        try{
            conn = DBManager.getConnection();
            String sql = "SELECT post_code,user_code,title,content,update_date FROM group_posts WHERE group_code =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,groupCode);

            rs = pstmt.executeQuery();

            list = new ArrayList<GroupPostResponseDto>();

            while(rs.next()){
                String postCode = rs.getString(1);
                String userCode = rs.getString(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                String updateDate = rs.getString(5);

                GroupPostResponseDto gp = new GroupPostResponseDto(postCode,groupCode,userCode,title,content,updateDate);
                list.add(gp);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBManager.close(conn,pstmt,rs);
        }

        return list;
    }
}
