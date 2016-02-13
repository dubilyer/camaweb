package daoData;

import java.util.*;

public class ParentData extends UserData {
Set<Integer> ChildrenIds;

public ParentData(int id, boolean enabled, String firstName, String lastName, String description, String email,
		Date regDate, Date lastLoginDate, String username, Set<Integer> childrenIds) {
	super(id, enabled, firstName, lastName, description, email, regDate, lastLoginDate, username);
	ChildrenIds = childrenIds;
}

public Set<Integer> getChildrenIds() {
	return ChildrenIds;
}

public void setChildrenIds(Set<Integer> childrenIds) {
	ChildrenIds = childrenIds;
}

}
