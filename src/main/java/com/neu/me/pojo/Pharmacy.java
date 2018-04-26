package com.neu.me.pojo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="PHARMACY")
@PrimaryKeyJoinColumn(name="personId")
public class Pharmacy extends person {
	
	@Column(name="pharmaName")
	private String pharmaName;
	
	@Column(name="manager")
	private String pharmaManager;
	
	@Column(name="ssn")
	private int ssn;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="personId")
	private Set<PharmaMedicine> listOfMedicine=new HashSet<PharmaMedicine>();
	
	public Pharmacy() {
	
			listOfMedicine = new HashSet<PharmaMedicine>();
		}

	public String getPharmaName() {
		return pharmaName;
	}

	public void setPharmaName(String pharmaName) {
		this.pharmaName = pharmaName;
	}

	public String getPharmaManager() {
		return pharmaManager;
	}

	public void setPharmaManager(String pharmaManager) {
		this.pharmaManager = pharmaManager;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Set<PharmaMedicine> getListOfMedicine() {
		return listOfMedicine;
	}

	public void setListOfMedicine(Set<PharmaMedicine> listOfMedicine) {
		this.listOfMedicine = listOfMedicine;
	}
	

}
