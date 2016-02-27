package dao;

import java.io.Serializable;
import java.util.*;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class User implements UserDetails, Serializable {
	@Id
	@GeneratedValue
	int id;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
//	@OneToMany(mappedBy = "user")
//	private List<Authority> authorities;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private String firstName;
	private String lastName;
	private String password;
	int secretCode;
	String description;
	String email;
	@Temporal(TemporalType.TIMESTAMP)
	Date regDate;
	@Temporal(TemporalType.TIMESTAMP)
	Date lastLoginDate;
	private String username; // login(telephone)

	public User() {
		super();
	}

		
	public User(String firstName, String lastName, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.regDate = new Date();
		secretCodeGenerator();
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		
		
		}


	@Override
	public String toString() {
		return "User [id=" + id + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + ", firstName="
				+ firstName + ", lastName=" + lastName + ", password=" + password + ", secretCode=" + secretCode
				+ ", description=" + description + ", email=" + email + ", regDate=" + regDate + ", lastLoginDate="
				+ lastLoginDate + ", username=" + username + "]";
	}


	public void secretCodeGenerator() {
		Random rnd = new Random();
		int code = 10000 + rnd.nextInt(89999);
		this.secretCode = code;
		this.enabled = false;
		//Sending secret code via sender utility
	}

	public boolean checkSecretCode(int secCode){
		if(this.secretCode == secCode){
			this.enabled = true;
			return true;
		}
		else
			return false;
	}


	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}


	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


//	public List<Authority> getAuthorities() {
//		return authorities;
//	}
//
//
//	public void setAuthorities(List<Authority> authorities) {
//		this.authorities = authorities;
//	}
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSecretCode() {
		return secretCode;
	}


	public void setSecretCode(int secretCode) {
		this.secretCode = secretCode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public Date getLastLoginDate() {
		return lastLoginDate;
	}


	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getId() {
		return id;
	}
	
	
}
