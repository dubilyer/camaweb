package dao;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

//@SuppressWarnings("serial")
//@Entity
public class Authority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = -6291981467059992770L;
	//	@Id
	String authority;
//	@ManyToOne
	User user;
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	public Authority() {
		super();
	}
	
	

}
