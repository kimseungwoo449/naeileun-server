package introduction.model;

import user.model.UserDao;

public class IntroductionRequestDto {
    UserDao userDao = UserDao.getInstance();

    private int documentNumber;
    private String title;
    private String head;
    private String body;
    private int documentCode;
    private int userCode;

    public IntroductionRequestDto(int documentNumber, String title, String head, String body, int documentCode, int userCode) {
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userCode;
    }

    public IntroductionRequestDto(int documentNumber, String title, String head, String body, int documentCode, String userId) {
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userDao.findUserCodeById(userId);
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
}
