package study.model.groupMember;

public class GroupMemberResponseDto {
	String groupCode;
	String userCode;
	String memberCode;

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
	
	
	
}
