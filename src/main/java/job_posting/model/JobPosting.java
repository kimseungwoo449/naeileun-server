package job_posting.model;

import java.sql.Date;

public class JobPosting {
    private int postingId;
    private int userCode;
    private String companyName;
    private String position;
    private Date startDate;
    private Date endDate;
    private String description;
    private String status;
    public JobPosting() {

    }
    public JobPosting(int postingId, int userCode, String companyName, String position, Date startDate, Date endDate, String description, String status) {
        this.postingId = postingId;
        this.userCode = userCode;
        this.companyName = companyName;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
    }

    public int getPostingId() {
        return postingId;
    }

    public int getUserCode() {
        return userCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
