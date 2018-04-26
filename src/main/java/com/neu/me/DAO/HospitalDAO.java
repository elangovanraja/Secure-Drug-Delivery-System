package com.neu.me.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.me.Exception.PException;
import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Hospital;

public class HospitalDAO extends DAO {
	
	public Hospital create(String HosName,String address,Set<Doctors> listOfDoctors)throws PException{
		try {	
			begin();
			Hospital hospital = new Hospital();
			hospital.setHosName(HosName);
			hospital.setAddress(address);
			hospital.setListOfDoctors(listOfDoctors);
			getSession().save(hospital);
			commit();
			return hospital;
		}
		catch(HibernateException e) {
			rollback();
	        
	        throw new PException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	public List<Hospital> getHospital() throws PException {
		try {
			begin();
			Query q=getSession().createQuery("from Hospital");
			List<Hospital> hospital=new ArrayList<Hospital>();
			hospital=q.list();
			commit();
			return hospital;
		}
		catch(HibernateException e){
			rollback();
			throw new PException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	

}
