package com.neu.me.pojo;


import javax.persistence.*;

@Entity
@Table(name="PersonTBL")
@Inheritance(strategy=InheritanceType.JOINED)
public class person {
	
	@Id
	@GeneratedValue
	@Column(name="personId", unique=true, nullable=false)
	private int personId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="type")
	private String type;
	
	@Column(name="email")
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public person(){
		
	}

	

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
