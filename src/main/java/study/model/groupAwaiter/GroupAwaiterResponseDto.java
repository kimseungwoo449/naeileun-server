package study.model.groupAwaiter;

public class GroupAwaiterResponseDto {
    String userCode;
    String groupCode;
    String comment;
    String userId;

    public GroupAwaiterResponseDto() {
    }

    public GroupAwaiterResponseDto(String userCode, String groupCode, String comment) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.comment = comment;
    }

    public GroupAwaiterResponseDto(String userCode, String groupCode, String comment, String userId) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.comment = comment;
        this.userId = userId;
    }

    public GroupAwaiterResponseDto(GroupAwaiter ga) {
        this.userCode = ga.getUserCode();
        this.groupCode = ga.getGroupCode();
        this.comment = ga.getComment();
        this.userId = ga.getUserId();
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
