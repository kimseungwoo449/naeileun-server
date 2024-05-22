package study.model.studyGroup;

public class StudyGroupResponseDto {
	String groupCode;
	String name;
	String decription;
	String adminCode;
	String isPublic;
	
	public StudyGroupResponseDto() {
		super();
	}
	
	public StudyGroupResponseDto(String groupCode, String name, String adminCode, String isPublic) {
		super();
		this.groupCode = groupCode;
		this.name = name;
		this.adminCode = adminCode;
		this.isPublic = isPublic;
	}
	
	public StudyGroupResponseDto(String groupCode, String name, String decription, String adminCode, String isPublic) {
		super();
		this.groupCode = groupCode;
		this.name = name;
		this.decription = decription;
		this.adminCode = adminCode;
		this.isPublic = isPublic;
	}
	
	public StudyGroupResponseDto(StudyGroup sg) {
		this.groupCode = sg.getGroupCode();
		this.name = sg.getName();
		this.decription = sg.getDecription();
		this.adminCode = sg.getAdminCode();
		this.isPublic = sg.getIsPublic();
		
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
	
	
}
