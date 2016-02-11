package dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Administrator extends User {
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "administrator")
	List<Manager> managers;
	
	public Administrator() {
		super();
	}
	
	public Administrator(String firstName, String lastName, String username) {
		super(firstName, lastName, username);
		managers = new LinkedList<>();
	}

	public List<Manager> getManagers() {
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}
	
	
}
