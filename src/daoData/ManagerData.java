package daoData;

import java.util.Date;

public class ManagerData extends UserData {
	
	int adminId;
	int eduOrgId;
	String position;
	public ManagerData(int id, boolean enabled, String firstName, String lastName, String description, String email,
			Date regDate, Date lastLoginDate, String username, int adminId, int eduOrgId, String position) {
		super(id, enabled, firstName, lastName, description, email, regDate, lastLoginDate, username);
		this.adminId = adminId;
		this.eduOrgId = eduOrgId;
		this.position = position;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getEduOrgId() {
		return eduOrgId;
	}
	public void setEduOrgId(int eduOrgId) {
		this.eduOrgId = eduOrgId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
