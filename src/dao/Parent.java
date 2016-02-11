package dao;

import java.util.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Parent extends User{
	
	@OneToMany(mappedBy="parent")
	private Set<Child> children;
	
	public Parent() {
		super();
	}

	public Parent(String firstName, String lastName, String username) {
		super(firstName, lastName, username);
		}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}
	
	public void addChild(Child child){
		this.children.add(child);
	}
	
	}
