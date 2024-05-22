package study.model.groupMember;

public class GroupMember {
	String groupCode;
	String userCode;
	String memberCode;
	
	public GroupMember(String groupCode, String userCode, String memberCode) {
		super();
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.memberCode = memberCode;
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
	
	
}
