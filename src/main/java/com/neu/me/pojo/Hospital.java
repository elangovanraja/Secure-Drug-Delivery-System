package com.neu.me.pojo;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="Hospital")
public class Hospital {
	
	@Id
	@GeneratedValue
	@Column(name="hosID",unique=true, nullable=false)
	private String hosID;
	
	@Column(name="HosName")
	private String HosName;
	
	@Column(name="address")
	private String address;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="hospital")
	private Set<Doctors> listOfDoctors = new HashSet<Doctors>();

	public String getHosID() {
		return hosID;
	}

	public void setHosID(String hosID) {
		this.hosID = hosID;
	}

	

	public String getHosName() {
		return HosName;
	}

	public void setHosName(String hosName) {
		HosName = hosName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Doctors> getListOfDoctors() {
		return listOfDoctors;
	}

	public void setListOfDoctors(Set<Doctors> listOfDoctors) {
		this.listOfDoctors = listOfDoctors;
	}
	
	

}
