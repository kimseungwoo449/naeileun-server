package introduction.model;

public class Introduction {
    private int documentNumber;
    private String title;
    private String head;
    private String body;
    private int documentCode;
    private int userCode;

    public Introduction(int documentNumber, String title, String head, String body, int documentCode, int userCode) {
        this.documentNumber = documentNumber;
        this.title = title;
        this.head = head;
        this.body = body;
        this.documentCode = documentCode;
        this.userCode = userCode;
    }

    public Introduction() {

    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public int getDocumentCode() {
        return documentCode;
    }

    public int getUserCode() {
        return userCode;
    }
}
