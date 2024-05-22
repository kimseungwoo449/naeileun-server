package study.model.studyGroup;

public class StudyGroupRequestDto {
	String groupCode;
	String name;
	String decription;
	String adminCode;
	String isPublic;
	String created_date;
	
	public StudyGroupRequestDto() {
		super();
	}
	
	public StudyGroupRequestDto(String groupCode, String name, String decription, String adminCode, String isPublic,
			String created_date) {
		super();
		this.groupCode = groupCode;
		this.name = name;
		this.decription = decription;
		this.adminCode = adminCode;
		this.isPublic = isPublic;
		this.created_date = created_date;
	}
	
	public StudyGroupRequestDto(String groupCode, String name, String adminCode, String isPublic) {
		super();
		this.groupCode = groupCode;
		this.name = name;
		this.adminCode = adminCode;
		this.isPublic = isPublic;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	
	
	
}
