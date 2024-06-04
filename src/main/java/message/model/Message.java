package message.model;

import java.sql.Timestamp;

public class Message {
    private int messageCode;
    private int sendUserCode;
    private int receiveUserCode;
    private String content;
    private Timestamp sendDate;
    private boolean isChecked;


    public Message(int messageCode, int sendUserCode, int receiveUserCode,  String content, Timestamp sendDate, boolean isChecked) {
        this.messageCode = messageCode;
        this.sendUserCode = sendUserCode;
        this.receiveUserCode = receiveUserCode;
        this.content = content;
        this.sendDate = sendDate;
        this.isChecked = isChecked;
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

    public String getContent() {
        return content;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public boolean isChecked() {return isChecked;}
}
