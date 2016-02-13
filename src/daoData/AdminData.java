package daoData;

import java.util.*;

public class AdminData extends UserData {
Set<Integer> managersIds;

public AdminData(int id, boolean enabled, String firstName, String lastName, String description, String email,
		Date regDate, Date lastLoginDate, String username, Set<Integer> managersIds) {
	super(id, enabled, firstName, lastName, description, email, regDate, lastLoginDate, username);
	this.managersIds = managersIds;
}

public Set<Integer> getManagersIds() {
	return managersIds;
}

public void setManagersIds(Set<Integer> managersIds) {
	this.managersIds = managersIds;
}

}
