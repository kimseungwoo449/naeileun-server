package study.model.groupPost;

public class GroupPostResponseDto {
    String postCode;
    String userCode;
    String groupCode;
    String title;
    String content;
    String updateDate;

    public GroupPostResponseDto() {
    }

    public GroupPostResponseDto(String postCode, String userCode, String groupCode, String title, String content, String updateDate) {
        this.postCode = postCode;
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
    }

    public GroupPostResponseDto(GroupPost groupPost) {
        this.postCode = groupPost.getPostCode();
        this.userCode = groupPost.getUserCode();
        this.groupCode = groupPost.getGroupCode();
        this.title = groupPost.getTitle();
        this.content = groupPost.getContent();
        this.updateDate = groupPost.getUpdateDate();
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
