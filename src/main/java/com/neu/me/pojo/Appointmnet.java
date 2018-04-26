package com.neu.me.pojo;
import javax.persistence.*;

@Entity
@Table(name="Appointment")
public class Appointmnet {
	
	@Id
	@GeneratedValue
	@Column(name="aptID", nullable=false, unique=true)
	private int aptID;
	@Column(name="status")
	private String status;
	private int doctor;
	private int patient;
	
	
	public int getAptID() {
		return aptID;
	}
	public void setAptID(int aptID) {
		this.aptID = aptID;
	}
	public int getDoctor() {
		return doctor;
	}
	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}
	public int getPatient() {
		return patient;
	}
	public void setPatient(int patient) {
		this.patient = patient;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
