package com.neu.me.pojo;
import javax.persistence.*;

@Entity
@Table(name="PresMedicinesTBL")
public class PresMedicines {
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
	@Column(name="quantity")
	private int quantity;
	private int prescriptions;	
	private int medicineId;
	
	public PresMedicines(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public int getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(int prescriptions) {
		this.prescriptions = prescriptions;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	
	

}
