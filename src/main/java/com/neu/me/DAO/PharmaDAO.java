package com.neu.me.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.me.Exception.PException;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.Prescriptions;
public class PharmaDAO extends DAO {

	public Pharmacy create( String password, String personName, String type, String userName, String pharmacyName, int ssn) throws PException {
		try {	
			begin();
			Pharmacy pharmacy = new Pharmacy();
			pharmacy.setPwd(password);
			pharmacy.setName(personName);
			pharmacy.setType(type);
			pharmacy.setUserName(userName);
			pharmacy.setPharmaName(pharmacyName);
			pharmacy.setSsn(ssn);
			getSession().save(pharmacy);
			commit();
			return pharmacy;
		}
		catch(HibernateException e) {
			rollback();
	        //throw new AdException("Could not create user " + username, e);
	        throw new PException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<Pharmacy> getPharmacy() throws PException {
		try {
			begin();
			Query q=getSession().createQuery("from Pharmacy");
			List<Pharmacy> persons=new ArrayList<Pharmacy>();
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
	
	public List<Prescriptions> getPrescriptions(){
		try{
			begin();
			Query q=getSession().createQuery("from Prescriptions");
			List<Prescriptions> prescriptions=new ArrayList<Prescriptions>();
			prescriptions=q.list();
			commit();
			return prescriptions;
		}
		catch(Exception E){
			E.printStackTrace();
			
		}
		finally{
			close();
		}
		return null;
		
	}
	
	

}
