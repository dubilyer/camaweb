package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class EducationOrganization {
	@Embedded
	Address address;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="eduOrg")
	Set<Educator> educators = new HashSet<>();

	@Id
	@GeneratedValue
	int eduOrgId;

	public int getEduOrgId() {
		return eduOrgId;
	}

	@OneToMany(fetch=FetchType.EAGER,mappedBy="eduOrg")
	Set<StudyGroup> groups;

	@OneToOne
	Manager manager;

	String name;

	String type;

	public EducationOrganization() {
		super();
	}

	public EducationOrganization(String name, String type, Address address) {
		super();
//		this.eduOrgId = eduOrgId;
		this.name = name;
		this.type = type;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public Set<Educator> getEducators() {
		return educators;
	}

	public Set<StudyGroup> getGroups() {
		return groups;
	}

	public Manager getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setEducators(Set<Educator> educators) {
		this.educators = educators;
	}
	
	public void setGroups(Set<StudyGroup> groups) {
		this.groups = groups;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
