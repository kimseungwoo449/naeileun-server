package study.model.groupPost;

public class GroupPostRequestDto {
    private String postCode;
    private String userCode;
    private String groupCode;
    private String title;
    private String content;
    private String writeDate;
    private String updateDate;
    private String userId;

    public GroupPostRequestDto() {
    }

    public GroupPostRequestDto(String groupCode, String userCode, String title, String content) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.title = title;
        this.content = content;
    }

    public GroupPostRequestDto(String userCode, String groupCode, String title, String content, String userId) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public GroupPostRequestDto(String postCode, String userCode, String groupCode, String title, String content, String writeDate, String updateDate) {
        this.postCode = postCode;
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
