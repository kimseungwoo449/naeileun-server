package message.model;

import user.model.UserDao;

import java.sql.Timestamp;

public class MessageResponseDto {
    private int messageCode;
    private int sendUserCode;
    private int receiveUserCode;
    private String content;
    private Timestamp sendDate;
    private boolean isChecked;

    public MessageResponseDto(int messageCode, int sendUserCode, int receiveUserCode, String content, Timestamp sendDate, boolean isChecked) {
        this.messageCode = messageCode;
        this.sendUserCode = sendUserCode;
        this.receiveUserCode = receiveUserCode;
        this.content = content;
        this.sendDate = sendDate;
        this.isChecked = isChecked;
    }

    public MessageResponseDto() {
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
