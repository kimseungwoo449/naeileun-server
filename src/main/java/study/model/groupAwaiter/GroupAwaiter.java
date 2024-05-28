package study.model.groupAwaiter;

public class GroupAwaiter {
    String userCode ;
    String groupCode;
    String comment;
    String userId;

    public GroupAwaiter(String userCode, String groupCode, String comment, String userId) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.comment = comment;
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getComment() {
        return comment;
    }

    public String getUserId() {
        return userId;
    }
}
