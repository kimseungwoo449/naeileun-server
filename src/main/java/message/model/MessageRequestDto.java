package message.model;

import org.json.JSONObject;
import user.model.UserDao;

import java.sql.Timestamp;

public class MessageRequestDto {
    private int messageCode;
    private int sendUserCode;
    private int receiveUserCode;
    private String content;
    private Timestamp sendDate;
    private boolean isChecked;

    public MessageRequestDto(int messageCode, int sendUserCode, int receiveUserCode, String content, Timestamp sendDate ,boolean isChecked) {
        this.messageCode = messageCode;
        this.sendUserCode = sendUserCode;
        this.receiveUserCode = receiveUserCode;
        this.content = content;
        this.sendDate = sendDate;
        this.isChecked = isChecked;
    }

    public MessageRequestDto() {
    }

    public MessageRequestDto(JSONObject reqObj){
        UserDao userDao = UserDao.getInstance();
        this.sendUserCode = userDao.findUserCodeById(reqObj.getString("user_id"));
        this.receiveUserCode = userDao.findUserCodeById(reqObj.getString("target_id"));
        this.content = reqObj.getString("content");
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public int getSendUserCode() {
        return sendUserCode;
    }

    public void setSendUserCode(String userId){
        UserDao userDao = UserDao.getInstance();
        this.sendUserCode = userDao.findUserCodeById(userId);
    }

    public void setSendUserCode(int sendUserCode) {
        this.sendUserCode = sendUserCode;
    }

    public int getReceiveUserCode() {
        return receiveUserCode;
    }

    public void setReceiveUserCode(String userId){
        UserDao userDao = UserDao.getInstance();
        this.receiveUserCode = userDao.findUserCodeById(userId);
    }

    public void setReceiveUserCode(int receiveUserCode) {
        this.receiveUserCode = receiveUserCode;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
