package job_posting.model;

import java.sql.Date;

public class JobPostingResponseDto {
    private int postingId;
    private int userCode;
    private String companyName;
    private String jobTitle;
    private Date applicationStart;
    private Date applicationEnd;
    private String description;
    private String status;
    public JobPostingResponseDto() {

    }
    public JobPostingResponseDto(int postingId, int userCode, String companyName, String jobTitle, Date applicationStart, Date applicationEnd, String description, String status) {
        this.postingId = postingId;
        this.userCode = userCode;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
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

    public Date getApplicationStart() {
        return applicationStart;
    }

    public void setApplicationStart(Date applicationStart) {
        this.applicationStart = applicationStart;
    }

    public Date getApplicationEnd() {
        return applicationEnd;
    }

    public void setApplicationEnd(Date applicationEnd) {
        this.applicationEnd = applicationEnd;
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
