package dao;

import java.util.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Educator extends User {
		
	@ManyToOne
	@JoinColumn(name="eduOrg_id")
	EducationOrganization eduOrg;
	
	@ManyToMany(fetch=FetchType.EAGER)
	//@JoinTable(name="groups_educators", joinColumns={@JoinColumn(name="educator_id")}, 
	//inverseJoinColumns={@JoinColumn(name="group_id")})
	Set<StudyGroup> groups;
	
	String subject;
	
	public Educator() {
		super();
	}

	public Educator(String firstName, String lastName, String username, String description, Manager creator, 
			Set<StudyGroup> groups) {
		super(firstName, lastName, username);
		Manager mngr = (Manager)creator;
		this.eduOrg = mngr.getEduOrg();
		this.groups = groups;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public EducationOrganization getEduOrg() {
		return eduOrg;
	}

	public void setEduOrg(EducationOrganization eduOrg) {
		this.eduOrg = eduOrg;
	}

	public Set<StudyGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<StudyGroup> groups) {
		this.groups = groups;
	}

	public void addGroup(StudyGroup studyGroup) {
		groups.add(studyGroup);
		
	}

	@Override
	public String toString() {
		return "Educator [id=" + id + ", eduOrg=" + eduOrg + ", subject=" + subject + "]";
	}



}
