package resume.model;

import java.sql.Timestamp;

public class Resume {
	private int resumeCode;
	private int userCode;
	private String name;
	private String title;
	private int userAge;
	private String academicCareer;
	private String career;
	private String skill;
	private String cetificate;
	private String language;
	private String award;
	private Timestamp writeDate;
	private Timestamp updateDate;
	
	public Resume(int resumeCode, int userCode, String name, String title, int userAge, String academicCareer, String career, String skill, String cetificate, String language, String award,
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
		this.cetificate = cetificate;
		this.language = language;
		this.award = award;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}
	
	public Resume() {
		
	}

	public int getResumeCode() {
		return resumeCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public int getUserAge() {
		return userAge;
	}

	public String getAcademicCareer() {
		return academicCareer;
	}

	public String getCareer() {
		return career;
	}

	public String getSkill() {
		return skill;
	}

	public String getCetificate() {
		return cetificate;
	}

	public String getLanguage() {
		return language;
	}

	public String getAward() {
		return award;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}
	
	
}
