package study.model.studyGroup;

public class StudyGroup {
    private String groupCode;
    private String name;
    private String decription;
    private String adminCode;
    private String isPublic;
    private String autoMemberAccess;

    public StudyGroup() {
        super();
    }

    public StudyGroup(String groupCode, String name, String adminCode, String isPublic, String autoMemberAccess) {
        super();
        this.groupCode = groupCode;
        this.name = name;
        this.adminCode = adminCode;
        this.isPublic = isPublic;
        this.autoMemberAccess = autoMemberAccess;
    }

    public StudyGroup(String groupCode, String name, String decription, String adminCode, String isPublic, String autoMemberAccess) {
        super();
        this.groupCode = groupCode;
        this.name = name;
        this.decription = decription;
        this.adminCode = adminCode;
        this.isPublic = isPublic;
        this.autoMemberAccess = autoMemberAccess;
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

    public String getAutoMemberAccess() {
        return autoMemberAccess;
    }
}
