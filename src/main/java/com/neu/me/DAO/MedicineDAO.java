package com.neu.me.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.me.Exception.PException;
import com.neu.me.pojo.Medicines;
import com.neu.me.pojo.PharmaMedicine;
import com.neu.me.pojo.PresMedicines;
import com.neu.me.pojo.person;
public class MedicineDAO extends DAO {
	public List<PresMedicines> getPresMed(int S) {
		try {
			begin();
			Query q = getSession().createQuery("from PresMedicines where prescriptions =:S");
			q.setInteger("S", S);
			List<PresMedicines> listPres = q.list();
			commit();
			return listPres;
		}
		catch(HibernateException e){
			rollback();
			try {
				throw new PException("Exception while fetching user: " + e.getMessage());
			} catch (PException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
			close();
		}
		return null;
		
	}
	
	public Medicines create(String medicineName, String quantity, String proposition) throws PException {
	
	try {	
		begin();
		Medicines medicine = new Medicines();
		medicine.setMedName(medicineName);
		medicine.setMedQuantity(quantity);
		medicine.setMedProposition(proposition);
		getSession().save(medicine);
		commit();
		return medicine;
	}
	catch(HibernateException e) {
		rollback();
        //throw new AdException("Could not create user " + username, e);
        throw new PException("Exception while creating user: " + e.getMessage());
	}
	}
	
	public List<Medicines> getMedicine() throws PException {
		try {
			begin();
			Query q=getSession().createQuery("from Medicines");
			List<Medicines> persons=new ArrayList<Medicines>();
			persons=q.list();
			commit();
			return persons;
		}
		catch(HibernateException e){
			rollback();
			throw new PException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<PharmaMedicine> getPharMed(int s) {
		try {
			begin();
			Query q=getSession().createQuery("from PharmaMedicine where personId = :s");
			q.setInteger("s", s);
			List<PharmaMedicine> list = new ArrayList<PharmaMedicine>();
			list=q.list();
			
			commit();
			return list;
		}
		catch(HibernateException e){
			rollback();
			return null;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<PharmaMedicine> getPharMed1(String s) {
		try {
			begin();
			Query q=getSession().createQuery("from PharmaMedicine where medicineId = (select id from Medicines where medName =:s)");
			q.setString("s", s);
			//List<PharmacyMedicines> list = new ArrayList<PharmacyMedicines>();
			List<PharmaMedicine> pm = q.list();
			
			commit();
			return pm;
		}
		catch(HibernateException e){
			rollback();
			System.out.println("naveennnnnnn");
			return null;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public int updatePharmacyMedicine(int s, int d, int f) {
		try {
			begin();
			Query q=getSession().createQuery("update PharmaMedicine set Quantity = :s where medicineId = :d and personId = :f");
			q.setInteger("s", s);
			q.setInteger("d", d);
			q.setInteger("f", f);
			//List<PharmacyMedicines> list = new ArrayList<PharmacyMedicines>();
			int result = q.executeUpdate();
			
			commit();
			return result;
		}
		catch(HibernateException e){
			rollback();
			return -1;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public PharmaMedicine insertPharmacyMedicine(int quantity, int medicineId, int pharmacyId) {
		try {
			begin();
			PharmaMedicine pm = new PharmaMedicine();
			pm.setMedicineId(medicineId);
			pm.setPersonId(pharmacyId);
			pm.setQuantity(quantity);
			getSession().save(pm);
			commit();
			return pm;
		}
		catch(HibernateException e){
			rollback();
			return null;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	public Medicines getMed(String medName)throws PException{
		try{
			begin();
			Query q=getSession().createQuery("from Medicines");
			List<Medicines> medicines=new ArrayList<Medicines>();
			medicines=q.list();
			commit();
			for(Medicines A:medicines){
				if(medName.equals(A.getMedName())){
					return A;
				}	
			}	
		}
		catch(HibernateException e){
			rollback();
			throw new PException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
		return null;
		}

}
