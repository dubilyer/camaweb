package daoData;

import dao.FamilyMember;

public class FamilyMemberData {
	int childId;
	String description;
	int familyMemberId;
	String firstName;
	String lastName;
	String phone;
	public FamilyMemberData(int childId, String description, int familyMemberId, String firstName, String lastName,
			String phone) {
		super();
		this.childId = childId;
		this.description = description;
		this.familyMemberId = familyMemberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	public FamilyMemberData(FamilyMember familyMember){
		this.childId = familyMember.getChild().getId();
		this.description = familyMember.getDescription();
		this.familyMemberId = familyMember.getFamilyMemberId();
		this.firstName = familyMember.getFirstName();
		this.lastName = familyMember.getLastName();
		this.phone = familyMember.getPhone();
	}
}
