package comment.model;

import java.sql.Timestamp;

public class CommentResponseDto {
    private int commentCode;
    private int userCode;
    private String userId;
    private int postCode;
    private String content;
    private Timestamp writeDate;
    private Timestamp updateDate;

    public CommentResponseDto() {

    }

    // userCode
    public CommentResponseDto(int commentCode, int userCode, int postCode, String content, Timestamp writeDate, Timestamp updateDate) {
        this.commentCode = commentCode;
        this.userCode = userCode;
        this.postCode = postCode;
        this.content = content;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    // userId
    public CommentResponseDto(int commentCode, String userId, int postCode, String content, Timestamp writeDate, Timestamp updateDate) {
        this.commentCode = commentCode;
        this.userId = userId;
        this.postCode = postCode;
        this.content = content;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    public CommentResponseDto(int commentCode, int userCode, int postCode, String content) {
        this.commentCode = commentCode;
        this.userCode = userCode;
        this.postCode = postCode;
        this.content = content;
    }

    public int getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(int commentCode) {
        this.commentCode = commentCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
