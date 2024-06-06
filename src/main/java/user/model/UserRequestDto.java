package user.model;

import java.lang.Boolean;
import java.sql.Timestamp;

public class UserRequestDto {
	private int userCode;
	private String id;
	private String password;
	private String name;
	private String resident_number;
	private int age;
	private String phone;
	private boolean admin;
	private String email;
	private Timestamp reg_Date;
	private Timestamp update_Date;
	public UserRequestDto() {
		
	}
	
	public UserRequestDto(int userCode,String id, String password, String name, String resident_number, String phone, boolean admin,
			String email) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.password = password;
		this.name = name;
		this.resident_number = resident_number;
		this.age = User.caculateAge(resident_number);
		this.phone = phone;
		this.admin = admin;
		this.email = email;
	}
	public UserRequestDto(String id, String password, String name, String resident_number, String phone, String admin,
			String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.resident_number = resident_number;
		this.age = User.caculateAge(resident_number);
		this.phone = phone;
		this.admin = Boolean.parseBoolean(admin);
		this.email = email;
	}
	public UserRequestDto(String id, String password, String name, String resident_number, String phone, Boolean admin,
			String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.resident_number = resident_number;
		this.age = User.caculateAge(resident_number);
		this.phone = phone;
		this.admin = admin;
		this.email = email;
	}

	public UserRequestDto(String id, String password, String name, String resident_number, String phone, boolean admin,
			String email, Timestamp reg_Date, Timestamp update_Date) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.resident_number = resident_number;
		this.age = User.caculateAge(resident_number);
		this.phone = phone;
		this.admin = admin;
		this.email = email;
		this.reg_Date = reg_Date;
		this.update_Date = update_Date;
	}
	
	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getResident_number() {
		return resident_number;
	}


	public void setResident_number(String resident_number) {
		this.resident_number = resident_number;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(Timestamp reg_Date) {
		this.reg_Date = reg_Date;
	}

	public Timestamp getUpdate_Date() {
		return update_Date;
	}

	public void setUpdate_Date(Timestamp update_Date) {
		this.update_Date = update_Date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	
}
