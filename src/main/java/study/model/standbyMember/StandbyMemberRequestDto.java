package study.model.standbyMember;

public class StandbyMemberRequestDto {
    private String userCode;
    private String groupCode;
    private String comment;
    private String sendDate;
    private String userId;

    public StandbyMemberRequestDto() {
    }

    public StandbyMemberRequestDto(String groupCode, String userCode) {
        this.groupCode = groupCode;
        this.userCode = userCode;
    }

    public StandbyMemberRequestDto(String userCode, String groupCode, String comment) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.comment = comment;
    }

    public StandbyMemberRequestDto(String userCode, String groupCode, String comment, String userId) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.comment = comment;
        this.userId = userId;
    }

    public StandbyMemberRequestDto(String userCode, String groupCode, String comment, String sendDate, String userId) {
        this.userCode = userCode;
        this.groupCode = groupCode;
        this.comment = comment;
        this.sendDate = sendDate;
        this.userId = userId;
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

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
