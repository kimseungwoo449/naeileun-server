package study.model.studyGroup;

public class StudyGroupResponseDto {
    private String groupCode;
    private String name;
    private String decription;
    private String adminCode;
    private String isPublic;
    private String autoMemberAccess;
    private String adminId;

    public StudyGroupResponseDto() {
        super();
    }

    public StudyGroupResponseDto(String groupCode, String name, String adminCode, String isPublic, String autoMemberAccess) {
        super();
        this.groupCode = groupCode;
        this.name = name;
        this.adminCode = adminCode;
        this.isPublic = isPublic;
        this.autoMemberAccess = autoMemberAccess;
    }

    public StudyGroupResponseDto(String groupCode, String name, String decription, String adminCode, String isPublic, String auto_member_access) {
        super();
        this.groupCode = groupCode;
        this.name = name;
        this.decription = decription;
        this.adminCode = adminCode;
        this.isPublic = isPublic;
        this.autoMemberAccess = auto_member_access;
    }

    public StudyGroupResponseDto(String groupCode, String name, String decription, String adminCode, String isPublic, String autoMemberAccess, String adminId) {
        this.groupCode = groupCode;
        this.name = name;
        this.decription = decription;
        this.adminCode = adminCode;
        this.isPublic = isPublic;
        this.autoMemberAccess = autoMemberAccess;
        this.adminId = adminId;
    }

    public StudyGroupResponseDto(StudyGroup sg) {
        this.groupCode = sg.getGroupCode();
        this.name = sg.getName();
        this.decription = sg.getDecription();
        this.adminCode = sg.getAdminCode();
        this.isPublic = sg.getIsPublic();
        this.autoMemberAccess = sg.getAutoMemberAccess();

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

    public String getAutoMemberAccess() {
        return autoMemberAccess;
    }

    public void setAutoMemberAccess(String autoMemberAccess) {
        this.autoMemberAccess = autoMemberAccess;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
