package study.model.groupMember;

public class GroupMemberResponseDto {
	String groupCode;
	String userCode;
	String memberCode;
	String userId;

	public GroupMemberResponseDto() {
		super();
	}

	public GroupMemberResponseDto(String groupCode, String userCode, String memberCode) {
		super();
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.memberCode = memberCode;
	}
	
	public GroupMemberResponseDto(GroupMember gm) {
		super();
		this.groupCode = gm.getGroupCode();
		this.userCode = gm.getUserCode();
		this.memberCode = gm.getMemberCode();
	}

	public GroupMemberResponseDto(String groupCode, String userCode, String memberCode, String userId) {
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.memberCode = memberCode;
		this.userId = userId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
