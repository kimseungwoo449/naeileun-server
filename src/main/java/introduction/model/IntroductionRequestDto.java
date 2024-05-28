package introduction.model;

import org.json.JSONObject;
import user.model.UserDao;

import java.sql.Timestamp;

public class IntroductionRequestDto {
    UserDao userDao = UserDao.getInstance();

    private int documentNumber;
    private String title;
    private String head;
    private String body;
    private int documentCode;
    private int userCode;
    private Timestamp writeDate;
    private Timestamp updateDate;


    public IntroductionRequestDto(int documentNumber, String title, String head, String body, int documentCode, int userCode, Timestamp writeDate, Timestamp updateDate) {
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userCode;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    public IntroductionRequestDto(int documentNumber, String title, String head, String body, int documentCode, String userId,Timestamp writeDate, Timestamp updateDate) {
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userDao.findUserCodeById(userId);
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    public IntroductionRequestDto(JSONObject obj){
        this.head = obj.getString("head");
        this.title = obj.getString("title");
        this.body = obj.getString("body");
        this.userCode = userDao.findUserCodeById(obj.getString("id"));
    }

    public IntroductionRequestDto() {
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
