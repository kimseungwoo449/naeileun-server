package message.model;

import java.sql.Timestamp;

public class Message {
    private int messageCode;
    private int sendUserCode;
    private int receiveUserCode;
    private String title;
    private String content;
    private Timestamp sendDate;

    public Message(int messageCode, int sendUserCode, int receiveUserCode, String title, String content, Timestamp sendDate) {
        this.messageCode = messageCode;
        this.sendUserCode = sendUserCode;
        this.receiveUserCode = receiveUserCode;
        this.title = title;
        this.content = content;
        this.sendDate = sendDate;
    }

    public Message() {
    }

    public int getMessageCode() {
        return messageCode;
    }

    public int getSendUserCode() {
        return sendUserCode;
    }

    public int getReceiveUserCode() {
        return receiveUserCode;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }
}
