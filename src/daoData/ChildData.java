package daoData;

import java.util.Set;

public class ChildData {
	int id;
	String firstName;
	String lastName;
	String address;
	int groupId;
	String[] comments;
	int parentId;
	Set<Integer> familyMembersIds;
	public ChildData(int id, String firstName, String lastName, String address, int groupId, String[] comments,
			int parentId, Set<Integer> familyMembersIds) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.groupId = groupId;
		this.comments = comments;
		this.parentId = parentId;
		this.familyMembersIds = familyMembersIds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String[] getComments() {
		return comments;
	}
	public void setComments(String[] comments) {
		this.comments = comments;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Set<Integer> getFamilyMembersIds() {
		return familyMembersIds;
	}
	public void setFamilyMembersIds(Set<Integer> familyMembersIds) {
		this.familyMembersIds = familyMembersIds;
	}
	
	
}
