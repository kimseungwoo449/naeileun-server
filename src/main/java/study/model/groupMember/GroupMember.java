package study.model.groupMember;

public class GroupMember {
	String groupCode;
	String userCode;
	String memberCode;
	String userId;
	
	public GroupMember(String groupCode, String userCode, String memberCode) {
		super();
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.memberCode = memberCode;
	}

	public GroupMember(String groupCode, String userCode, String memberCode, String userId) {
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.memberCode = memberCode;
		this.userId = userId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public String getUserId() {
		return userId;
	}
}
