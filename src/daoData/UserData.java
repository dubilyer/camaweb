package daoData;

import java.util.Date;

public class UserData {
	int id;
	private boolean enabled;
	private String firstName;
	private String lastName;
	String description;
	String email;
	Date regDate;
	Date lastLoginDate;
	private String username; // login(telephone)
	
	
	public UserData(int id, boolean enabled, String firstName, String lastName, String description, String email,
			Date regDate, Date lastLoginDate, String username) {
		super();
		this.id = id;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.email = email;
		this.regDate = regDate;
		this.lastLoginDate = lastLoginDate;
		this.username = username;
	}
	
	
	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
