package study.model.groupMember;

public class GroupMemberRequestDto {
	String groupCode;
	String userCode;
	String joinedDate;
	String memberCode;
	String userId;
	
	public GroupMemberRequestDto() {
		super();
	}

	public GroupMemberRequestDto(String groupCode){
		this.groupCode = groupCode;
	}

	public GroupMemberRequestDto(String groupCode, String userCode, String joinedDate, String memberCode) {
		super();
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.joinedDate = joinedDate;
		this.memberCode = memberCode;
	}

	public GroupMemberRequestDto(String groupCode, String userCode) {
		super();
		this.groupCode = groupCode;
		this.userCode = userCode;
	}

	public GroupMemberRequestDto(String groupCode, String userCode, String joinedDate, String memberCode, String userId) {
		this.groupCode = groupCode;
		this.userCode = userCode;
		this.joinedDate = joinedDate;
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

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
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
