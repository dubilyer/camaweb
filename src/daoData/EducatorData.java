package daoData;

import java.util.*;

public class EducatorData extends UserData {
int eduOrgId;
Set<Integer> groupsIds;
String subject;
public EducatorData(int id, boolean enabled, String firstName, String lastName, String description, String email,
		Date regDate, Date lastLoginDate, String username, int eduOrgId, Set<Integer> groupsIds, String subject) {
	super(id, enabled, firstName, lastName, description, email, regDate, lastLoginDate, username);
	this.eduOrgId = eduOrgId;
	this.groupsIds = groupsIds;
	this.subject = subject;
}
public int getEduOrgId() {
	return eduOrgId;
}
public void setEduOrgId(int eduOrgId) {
	this.eduOrgId = eduOrgId;
}
public Set<Integer> getGroupsIds() {
	return groupsIds;
}
public void setGroupsIds(Set<Integer> groupsIds) {
	this.groupsIds = groupsIds;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}

}
