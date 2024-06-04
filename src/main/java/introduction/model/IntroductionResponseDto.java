package introduction.model;

import user.model.UserDao;

import java.sql.Timestamp;

public class IntroductionResponseDto {


    private int documentNumber;
    private String title;
    private String head;
    private String body;
    private int documentCode;
    private int userCode;
    private Timestamp writeDate;
    private Timestamp updateDate;


    public IntroductionResponseDto(int documentNumber, String title, String head, String body, int documentCode, int userCode, Timestamp writeDate, Timestamp updateDate) {
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userCode;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    public IntroductionResponseDto(int documentNumber, String title, String head, String body, int documentCode, String userId,Timestamp writeDate, Timestamp updateDate) {
        UserDao userDao = UserDao.getInstance();
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userDao.findUserCodeById(userId);
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    public IntroductionResponseDto() {
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(int documentCode) {
        this.documentCode = documentCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setUserCode(String userId) {
        UserDao userDao = UserDao.getInstance();
        this.userCode = userDao.findUserCodeById(userId);
    }

    public Timestamp getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Timestamp writeDate) {
        this.writeDate = writeDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
