package dao;

import java.util.*;

import javax.persistence.*;

@Entity
public class Child {

	@Id
	@GeneratedValue
	int id;
	
	String firstName;
	String lastName;
	
	@Embedded
	Address address;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	StudyGroup group;
	
	@Column(name="comments")
	String[] comments;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	Parent parent;
	
	@OneToMany(mappedBy="child", fetch=FetchType.EAGER)
	Set<FamilyMember> familyMembers;
	

	public Child(String firstName, String lastName,String comment, StudyGroup group) {
		comments = new String[10];
		this.firstName = firstName;
		this.lastName = lastName;
		comments[0] = comment;
		this.group = group;
	}

	
	public Child() {
		super();
	}


	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Set<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Set<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public StudyGroup getGroup() {
		return group;
	}

	public void setGroup(StudyGroup group) {
		this.group = group;
	}


	
}   
