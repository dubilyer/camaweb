package dao;

import java.util.*;

import javax.persistence.*;

@Entity

public class StudyGroup {
	@Id
	@GeneratedValue
	int groupId;
	
	String groupName;
	
	@ManyToOne
	@JoinColumn(name="eduOrg_id")
	EducationOrganization eduOrg;
	
	
	@OneToMany(mappedBy="group",fetch = FetchType.EAGER)
	Set<Child> children;

	@ManyToMany(mappedBy="groups",fetch = FetchType.EAGER)
	//@JoinTable(name="groups_educators", joinColumns={@JoinColumn(name="group_id")}, 
	//inverseJoinColumns={@JoinColumn(name="educator_id")})
	Set<Educator> educators;

	public StudyGroup(String groupName, EducationOrganization eduOrg) {
		super();
		this.groupName = groupName;
		this.eduOrg = eduOrg;
		educators = new HashSet<Educator>();
	}

	public String getGroupName() {
		return groupName;
	}

	public StudyGroup() {
		super();
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public EducationOrganization getEduOrg() {
		return eduOrg;
	}

	public void setEduOrg(EducationOrganization eduOrg) {
		this.eduOrg = eduOrg;
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	public Set<Educator> getEducators() {
		return educators;
	}

	public void setEducators(Set<Educator> educators) {
		this.educators = educators;
	}

	public void addEducator(Educator educator) {
		this.educators.add(educator);		
	}

	@Override
	public String toString() {
		return "StudyGroup [groupId=" + groupId + ", groupName=" + groupName + ", eduOrg=" + eduOrg + ", children="
				+ children + "]";
	}

	public int getGroupId() {
		return groupId;
	}
	
}
