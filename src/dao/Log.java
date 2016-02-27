package dao;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Log {
	@Id
	@GeneratedValue
	long id;
	@Transient
	User creator;
	@Temporal(TemporalType.TIMESTAMP)
	Date creationTime;
	String text;
	String creatorName;
	int creatorId;
	Class<? extends User> creatorClass;

	
	public Log(User creator, String text) {
		super();
		this.creator = creator;
		this.text = text;
		this.creatorName=creator.getLastName();
		this.creatorId = creator.id;
		this.creatorClass = creator.getClass();
		this.creationTime = new Date();
	}

	public int getCreatorId() {
		return creatorId;
	}

	public Class<? extends User> getCreatorClass() {
		return creatorClass;
	}

	public Log() {
		super();
		this.creationTime = new Date();
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
