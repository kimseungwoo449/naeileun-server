package study.model.groupAwaiter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GroupAwaiterDao {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    private static GroupAwaiterDao instace = new GroupAwaiterDao();

    public static GroupAwaiterDao getInstace() {
        return instace;
    }
}
