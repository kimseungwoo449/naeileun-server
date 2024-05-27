package user.model;


import java.sql.Timestamp;
import java.time.LocalDate;

public class User {
	private String userCode;
	private String id;
	private String password;
	private String name;
	private String age;
	private String resident_number;
	private String phone;
	private boolean admin;
	private String email;
	private Timestamp reg_Date;
	private Timestamp update_Date;
	
	public User() {
		
	}
	
	
	public User(String userCode, String id, String password, String name, String age, String resident_number,
			String phone, boolean admin, String email) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.resident_number = resident_number;
		this.phone = phone;
		this.admin = admin;
		this.email = email;
	}


	public User(String id, String password, String name, String resident_number, String phone, boolean admin,
			String email, Timestamp reg_Date, Timestamp update_Date) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.resident_number = resident_number;
		this.age = caculateAge(resident_number);
		this.phone = phone;
		this.admin = admin;
		this.email = email;
		this.reg_Date = reg_Date;
		this.update_Date = update_Date;
	}
	public User(String id, String password, String name, String resident_number, String phone, boolean admin,
			String email, Timestamp reg_Date) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.resident_number = resident_number;
		this.age = caculateAge(resident_number);
		this.phone = phone;
		this.admin = admin;
		this.email = email;
		this.reg_Date = reg_Date;
		
	}
	public User(String id,  String name, String resident_number, String phone, boolean admin,
			String email, Timestamp reg_Date) {
		super();
		this.id = id;
		this.name = name;
		this.resident_number = resident_number;
		this.age = caculateAge(resident_number);
		this.phone = phone;
		this.admin = admin;
		this.email = email;
		this.reg_Date = reg_Date;
		
	}
	
	public String getUserCode() {
		return userCode;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getResident_number() {
		return resident_number;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getEmail() {
		return email;
	}

	public Timestamp getReg_Date() {
		return reg_Date;
	}

	public Timestamp getUpdate_Date() {
		return update_Date;
	}
	
	
	public String getAge() {
		return age;
	}


	public static String caculateAge(String residentNumber) {
			
	        int birthYear = Integer.parseInt(residentNumber.substring(0, 2));

	        // 주민등록번호 7번째 자리를 확인하여 2000년 이전과 이후 출생자를 구분
	        if (residentNumber.charAt(8) == '1' || residentNumber.charAt(8) == '2') {
	            birthYear += 1900;
	        } else {
	            birthYear += 2000;
	        }

	
	        int currentYear = LocalDate.now().getYear();

	
	        String age = ""+(currentYear - birthYear + 1);
	        return age;
	}
	
}
