package job_posting.model;

import java.sql.Date;

public class JobPostingRequestDto {
    private int postingId;
    private int userCode;
    private String companyName;
    private String jobTitle;
    private String position;
    private Date applicationStart;
    private Date applicationEnd;
    private String description;
    private String status;
    public JobPostingRequestDto() {

    }
    public JobPostingRequestDto(int postingId, int userCode, String companyName, String position, Date applicationStart, Date applicationEnd, String description, String status) {
        this.postingId = postingId;
        this.userCode = userCode;
        this.companyName = companyName;
        this.position = position;
        this.applicationStart = applicationStart;
        this.applicationEnd = applicationEnd;
        this.description = description;
        this.status = status;
    }

    public int getPostingId() {
        return postingId;
    }

    public void setPostingId(int postingId) {
        this.postingId = postingId;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getApplicationStart() {
        return applicationStart;
    }

    public void setApplicationStart(Date applicationStart) {
        this.applicationStart = applicationStart;
    }

    public Date getApplicationEnd() {
        return applicationEnd;
    }

    public void setApplicationEnd(Date endDate) {
        this.applicationEnd = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
