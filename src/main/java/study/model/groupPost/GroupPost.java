package study.model.groupPost;

public class GroupPost {
    String postCode;
    String userCode;
    String groupCode;
    String title;
    String content;
    String updateDate;

    public GroupPost(String postCode, String userCode, String groupCode, String title, String content, String updateDate) {
        this.postCode = postCode;
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUpdateDate() {
        return updateDate;
    }
}
