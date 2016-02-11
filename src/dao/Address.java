package dao;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Address implements Serializable{
String country;
String city;
String street;
String building;

public Address(String country, String city, String street, String building) {
	super();
	this.country = country;
	this.city = city;
	this.street = street;
	this.building = building;
}

public Address() {
	super();
	}

public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getBuilding() {
	return building;
}
public void setBuilding(String building) {
	this.building = building;
}

@Override
public boolean equals(Object obj) {
	Address addr = (Address) obj;
	if((this.city == addr.city) && (this.street == addr.street) && (this.building == addr.building))
		return true;
	return false;
}

}
