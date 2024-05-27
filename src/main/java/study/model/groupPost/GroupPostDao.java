package study.model.groupPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        return list;
    }
}
