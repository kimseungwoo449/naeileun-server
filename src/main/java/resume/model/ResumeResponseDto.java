package resume.model;

import java.sql.Timestamp;

public class ResumeResponseDto {
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
	private String phone;
	private String expectedSalary;
	private String expectedRegion;
	private boolean isNewbie;

	public ResumeResponseDto(int resumeCode, int userCode, String name, String title, int userAge, String academicCareer, String career, String skill, String certificate, String language, String award, Timestamp writeDate, Timestamp updateDate, String phone, String expectedSalary, String expectedRegion, boolean isNewbie) {
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
		this.phone = phone;
		this.expectedSalary = expectedSalary;
		this.expectedRegion = expectedRegion;
		this.isNewbie = isNewbie;
	}

	public ResumeResponseDto() {
		
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

	public void setUserCode(int userCode) {
		this.userCode = userCode;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getExpectedRegion() {
		return expectedRegion;
	}

	public void setExpectedRegion(String expectedRegion) {
		this.expectedRegion = expectedRegion;
	}

	public boolean isNewbie() {
		return isNewbie;
	}

	public void setNewbie(boolean newbie) {
		isNewbie = newbie;
	}
}
