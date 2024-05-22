package study.model.studyGroup;

public class StudyGroup {
	String groupCode;
	String name;
	String decription;
	String adminCode;
	String isPublic;
	public StudyGroup() {
		super();
	}
	public StudyGroup(String groupCode, String name, String adminCode, String isPublic) {
		super();
		this.groupCode = groupCode;
		this.name = name;
		this.adminCode = adminCode;
		this.isPublic = isPublic;
	}
	
	public StudyGroup(String groupCode, String name, String decription, String adminCode, String isPublic) {
		super();
		this.groupCode = groupCode;
		this.name = name;
		this.decription = decription;
		this.adminCode = adminCode;
		this.isPublic = isPublic;
	}
	
	public String getGroupCode() {
		return groupCode;
	}
	public String getName() {
		return name;
	}
	public String getDecription() {
		return decription;
	}
	public String getAdminCode() {
		return adminCode;
	}
	public String getIsPublic() {
		return isPublic;
	}
	
	
}
