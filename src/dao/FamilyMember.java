package dao;

import javax.persistence.*;

@Entity
public class FamilyMember {
	@ManyToOne
	@JoinColumn(name="child_id")
	Child child;
	
	String description;

@Id
@GeneratedValue
int familyMemberId;
String firstName;
String lastName;
String phone;
public FamilyMember() {
	super();
}

public FamilyMember(Child child, String firstName, String lastName, String phone, String description) {
	super();
	this.child = child;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.description = description;
}

public Child getChild() {
	return child;
}

public void setChild(Child child) {
	this.child = child;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getFamilyMemberId() {
	return familyMemberId;
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

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

@Override
public String toString() {
	return "FamilyMember [familyMemberId=" + familyMemberId + ", child=" + child + ", firstName=" + firstName
			+ ", lastName=" + lastName + ", phone=" + phone + ", description=" + description + "]";
}

}
