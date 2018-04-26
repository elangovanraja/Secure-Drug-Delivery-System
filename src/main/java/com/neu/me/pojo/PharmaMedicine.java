package com.neu.me.pojo;

import javax.persistence.*;

@Entity
@Table(name="PharmaMedicineTBL")
public class PharmaMedicine {
	@Id
	@GeneratedValue
	@Column(name="medicinePharmacyID", unique=true, nullable=false)
	private int id;
	
	private int personId;	
	private int medicineId;
	
	@Column(name="quantity")
	private int quantity;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	

}
