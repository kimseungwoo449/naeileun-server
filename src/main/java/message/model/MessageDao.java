package message.model;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public List<MessageResponseDto> getMessages(MessageRequestDto req) {
        List<MessageResponseDto> res = new ArrayList<MessageResponseDto>();
        try{
            conn = DBManager.getConnection();

            String sql = "SELECT * FROM messages WHERE send_user_code=? OR receive_user_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, req.getSendUserCode());
            pstmt.setInt(2, req.getReceiveUserCode());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int messageCode = rs.getInt("message_code");
                int sendUserCode = rs.getInt("send_user_code");
                int receiveUserCode = rs.getInt("receive_user_code");
                String content = rs.getString("content");
                Timestamp sendDate = rs.getTimestamp("send_date");
                boolean isChecked = rs.getBoolean("is_checked");

                MessageResponseDto dto = new MessageResponseDto(messageCode, sendUserCode, receiveUserCode, content, sendDate, isChecked);
                res.add(dto);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt,rs);
        }
        return res;
    }

    public boolean refineIsChecked(MessageRequestDto req) {
        try{
            conn = DBManager.getConnection();
            String sql = "UPDATE messages SET is_checked=TRUE WHERE send_user_code = ? AND receive_user_code = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, req.getSendUserCode());
            pstmt.setInt(2, req.getReceiveUserCode());

            pstmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt);
        }

        return false;
    }

    public boolean deleteMessageByUserCode(MessageRequestDto req) {
        try{
            conn = DBManager.getConnection();

            String sql = "DELETE FROM messages WHERE send_user_code=? OR receive_user_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, req.getSendUserCode());
            pstmt.setInt(2, req.getReceiveUserCode());
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
