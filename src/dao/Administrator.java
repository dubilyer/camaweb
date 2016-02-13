package dao;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Administrator extends User {
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "administrator")
	Set<Manager> managers;
	
	public Administrator() {
		super();
	}
	
	public Administrator(String firstName, String lastName, String username) {
		super(firstName, lastName, username);
		managers = new HashSet<Manager>();
	}

	public Set<Manager> getManagers() {
		return managers;
	}

	public void setManagers(Set<Manager> managers) {
		this.managers = managers;
	}
	
	
}
