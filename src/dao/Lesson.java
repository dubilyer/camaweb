package dao;

import java.util.*;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Lesson {
	@Id
	@GeneratedValue
	long lessonId;
	
	 @Temporal(TemporalType.TIMESTAMP)
	 @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date startTime;
	 
	 @OneToOne
	 @JoinColumn(name="groupId")
	StudyGroup group;
	 
	 @OneToMany
	Set<Child> absentChildren;
	 
	 @OneToOne
	 @JoinColumn(name="id")
	Educator educator;
	
	String subject;
		
	public Lesson() {
		super();
	}

	public Lesson(StudyGroup group, Set<Child> absentChildren, Educator educator) {
		super();
		this.startTime = new Date();
		this.group = group;
		this.absentChildren = absentChildren;
		this.educator = educator;
		this.subject = educator.getSubject();
	}

	public boolean addAbsentChild(Child child){
		return this.absentChildren.add(child);
	}
	
	public boolean removeChildFromAbsent(Child child){
		return this.absentChildren.remove(child);
	}
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public StudyGroup getGroup() {
		return group;
	}

	public void setGroup(StudyGroup group) {
		this.group = group;
	}

	public Set<Child> getAbsentChildren() {
		return absentChildren;
	}

	public void setAbsentChildren(Set<Child> absentChildren) {
		this.absentChildren = absentChildren;
	}

	public Educator getEducator() {
		return educator;
	}

	public void setEducator(Educator educator) {
		this.educator = educator;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
