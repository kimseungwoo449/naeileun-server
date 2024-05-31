package message.model;

import user.model.UserDao;

import java.sql.Timestamp;

public class MessageRequestDto {
    private int messageCode;
    private int sendUserCode;
    private int receiveUserCode;
    private String title;
    private String content;
    private Timestamp sendDate;

    public MessageRequestDto(int messageCode, int sendUserCode, int receiveUserCode, String title, String content, Timestamp sendDate) {
        this.messageCode = messageCode;
        this.sendUserCode = sendUserCode;
        this.receiveUserCode = receiveUserCode;
        this.title = title;
        this.content = content;
        this.sendDate = sendDate;
    }

    public MessageRequestDto() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
