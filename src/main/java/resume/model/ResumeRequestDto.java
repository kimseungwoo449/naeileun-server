package resume.model;

import java.sql.Timestamp;

import org.json.JSONObject;

import user.model.UserDao;

public class ResumeRequestDto {
	UserDao userDao = UserDao.getInstance();
	private int resumeCode;
	private int userCode;
	private String name;
	private String title;
	private int userAge;
	private String academicCareer;
	private String career;
	private String skill;
	private String certificate;
	private String language;
	private String award;
	private Timestamp writeDate;
	private Timestamp updateDate;
	
	public ResumeRequestDto(int resumeCode, int userCode, String name, String title, int userAge, String academicCareer, String career, String skill, String certificate, String language, String award,
			Timestamp writeDate, Timestamp updateDate) {
		super();
		this.resumeCode = resumeCode;
		this.userCode = userCode;
		this.name = name;
		this.title = title;
		this.userAge = userAge;
		this.academicCareer = academicCareer;
		this.career = career;
		this.skill = skill;
		this.certificate = certificate;
		this.language = language;
		this.award = award;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}
	
	public ResumeRequestDto() {
		
	}
	
	public ResumeRequestDto(JSONObject obj,String state) {
		if(state.equals("update")) {
			this.resumeCode = obj.getInt("resume_code");			
		}
		this.userCode = userDao.findUserCodeById(obj.getString("user_id"));
		this.name = obj.getString("name");
		this.title = obj.getString("title");
		this.userAge = obj.getInt("user_age");
		this.academicCareer = obj.getString("academic_career");
		this.career = obj.getString("career");
		this.skill = obj.getString("skill");
		this.certificate = obj.getString("certificate");
		this.language = obj.getString("language");
		this.award = obj.getString("award");
	}
	
	public int getResumeCode() {
		return resumeCode;
	}

	public void setResumeCode(int resumeCode) {
		this.resumeCode = resumeCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(String userId) {
		this.userCode = userDao.findUserCodeById(userId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getAcademicCareer() {
		return academicCareer;
	}

	public void setAcademicCareer(String academicCareer) {
		this.academicCareer = academicCareer;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
