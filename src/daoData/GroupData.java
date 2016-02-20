package daoData;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dao.Child;
import dao.EducationOrganization;
import dao.Educator;

public class GroupData {
int groupId;
	
	String groupName;
	
	int eduOrgId;

	Set<Integer> childrenIds;

	Set<Integer> educatorsIds;

	public GroupData(int groupId, String groupName, int eduOrg, Set<Integer> childrenIds,
			Set<Integer> educatorsIds) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.eduOrgId = eduOrg;
		this.childrenIds = childrenIds;
		this.educatorsIds = educatorsIds;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getEduOrg() {
		return eduOrgId;
	}

	public void setEduOrg(int eduOrg) {
		this.eduOrgId = eduOrg;
	}

	public Set<Integer> getChildrenIds() {
		return childrenIds;
	}

	public void setChildrenIds(Set<Integer> childrenIds) {
		this.childrenIds = childrenIds;
	}

	public Set<Integer> getEducatorsIds() {
		return educatorsIds;
	}

	public void setEducatorsIds(Set<Integer> educatorsIds) {
		this.educatorsIds = educatorsIds;
	}
	

}
