package com.neu.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="DoctorTBL")
@PrimaryKeyJoinColumn(name="personId")
public class Doctors extends person {
	
	@Column(name="quaification")
	private String quaification;
	
	private int hospital;
	

	public int getHospital() {
		return hospital;
	}

	public void setHospital(int hospital) {
		this.hospital = hospital;
	}

	@Column(name="ssn")
	private String ssn;
	
	@Column(name="specialized")
	private String specialized;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="doctor")
	private Set<Prescriptions> prescriptionSet = new HashSet<Prescriptions>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="doctor")
	private Set<Appointmnet> AppointmentSet = new HashSet<Appointmnet>();

	public String getQuaification() {
		return quaification;
	}

	public void setQuaification(String quaification) {
		this.quaification = quaification;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSpecialized() {
		return specialized;
	}

	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}

	public Set<Prescriptions> getPrescriptionSet() {
		return prescriptionSet;
	}

	public void setPrescriptionSet(Set<Prescriptions> prescriptionSet) {
		this.prescriptionSet = prescriptionSet;
	}

	public Set<Appointmnet> getAppointmentSet() {
		return AppointmentSet;
	}

	public void setAppointmentSet(Set<Appointmnet> appointmentSet) {
		AppointmentSet = appointmentSet;
	}
	
	
	

}
