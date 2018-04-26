package com.neu.me.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name="Patient")
@PrimaryKeyJoinColumn(name="personId")
public class Patient extends person
{
	@OneToMany(fetch=FetchType.EAGER, mappedBy="patient")
	private Set<Prescriptions> listOfPrescriptions = new HashSet<Prescriptions>();
	
	@Column(name="blood_Group")
	private String blood_Group;
	
	@Column(name="weight")
	private float weight;
	
	@Column(name="BP")
	private float BP;
	
	@Column(name="lastVisit")
	private String lastVisit;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="patient")
	private Set<Appointmnet> listOfAppointments = new HashSet<Appointmnet>();
	
	public Patient() {
		
			this.listOfPrescriptions = new HashSet<Prescriptions>();
		}

	public Set<Prescriptions> getListOfPrescriptions() {
		return listOfPrescriptions;
	}

	public void setListOfPrescriptions(Set<Prescriptions> listOfPrescriptions) {
		this.listOfPrescriptions = listOfPrescriptions;
	}

	public String getBlood_Group() {
		return blood_Group;
	}

	public void setBlood_Group(String blood_Group) {
		this.blood_Group = blood_Group;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getBP() {
		return BP;
	}

	public void setBP(float bP) {
		BP = bP;
	}

	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Set<Appointmnet> getListOfAppointments() {
		return listOfAppointments;
	}

	public void setListOfAppointments(Set<Appointmnet> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}
	
	
		
	
}
