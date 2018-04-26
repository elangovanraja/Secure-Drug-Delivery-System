package com.neu.me.pojo;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="MEDICINE")
public class Medicines {
	@Id
	@GeneratedValue
	@Column(name="medId", unique=true, nullable=false)
	private int id;
	
	@Column(name="medName")
	private String medName;
	
	@Column(name="medQuantity")
	private String medQuantity;
	
	@Column(name="medProposition")
	private String medProposition;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="medicineId")
	private Set<PharmaMedicine> listOfPharmacy = new HashSet<PharmaMedicine>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="medicineId")
	private Set<PresMedicines> listOnPrescription = new HashSet<PresMedicines>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getMedQuantity() {
		return medQuantity;
	}

	public void setMedQuantity(String medQuantity) {
		this.medQuantity = medQuantity;
	}

	public String getMedProposition() {
		return medProposition;
	}

	public void setMedProposition(String medProposition) {
		this.medProposition = medProposition;
	}

	public Set<PharmaMedicine> getListOfPharmacy() {
		return listOfPharmacy;
	}

	public void setListOfPharmacy(Set<PharmaMedicine> listOfPharmacy) {
		this.listOfPharmacy = listOfPharmacy;
	}

	public Set<PresMedicines> getListOnPrescription() {
		return listOnPrescription;
	}

	public void setListOnPrescription(Set<PresMedicines> listOnPrescription) {
		this.listOnPrescription = listOnPrescription;
	}


}
