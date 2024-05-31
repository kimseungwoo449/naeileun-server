package message.model;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MessageDao {
    private static MessageDao instance = new MessageDao();
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private MessageDao() {}
    public static MessageDao getInstance() {return instance;}

    public boolean sendMessage(MessageRequestDto dto) {
        try{
            conn = DBManager.getConnection();

            String sql = "INSERT INTO messages(send_user_code,receive_user_code,content) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getSendUserCode());
            pstmt.setInt(2, dto.getReceiveUserCode());
            pstmt.setString(3, dto.getContent());

            pstmt.execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt);
        }
        return false;
    }
}
