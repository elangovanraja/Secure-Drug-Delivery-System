package com.neu.me.pojo;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PrescriptionTBL")
public class Prescriptions {
	
	@Id
	@GeneratedValue
	@Column(name="presID",unique=true, nullable=false)
	private int presID;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="prescriptions")	
	private Set<PresMedicines> PresMedicinesSet = new HashSet<PresMedicines>();
	public Prescriptions() {
		PresMedicinesSet = new HashSet<PresMedicines>();
	}
	private int patient;
	private int doctor;
	public int getPresID() {
		return presID;
	}
	public void setPresID(int presID) {
		this.presID = presID;
	}
	public Set<PresMedicines> getPresMedicinesSet() {
		return PresMedicinesSet;
	}
	public void setPresMedicinesSet(Set<PresMedicines> presMedicinesSet) {
		PresMedicinesSet = presMedicinesSet;
	}
	public int getPatient() {
		return patient;
	}
	public void setPatient(int patient) {
		this.patient = patient;
	}
	public int getDoctor() {
		return doctor;
	}
	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}
	
	
	
}
