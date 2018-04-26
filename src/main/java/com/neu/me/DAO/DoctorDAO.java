package com.neu.me.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.me.Exception.PException;
import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Patient;
import com.neu.me.pojo.PresMedicines;
import com.neu.me.pojo.Prescriptions;
public class DoctorDAO extends DAO {

	public Doctors create( String password, String personName, String type, String userName, String specilization, String ssn) throws PException {
		try {	
			begin();
			Doctors doc = new Doctors();
			
			doc.setPwd(password);
			doc.setName(personName);
			doc.setType(type);
			doc.setUserName(userName);
			doc.setSpecialized(specilization);
			doc.setSsn(ssn);
			getSession().save(doc);
			commit();
			return doc;
		}
		catch(HibernateException e) {
			rollback();
	        
	        throw new PException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<Doctors> getDoctor() throws PException {
		try {
			begin();
			Query q=getSession().createQuery("from Doctors");
			List<Doctors> persons=new ArrayList<Doctors>();
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

	public Prescriptions create(Prescriptions p) {
		try {	
			begin();
			
			getSession().save(p);
			for(PresMedicines pm : p.getPresMedicinesSet()) {
				pm.setPrescriptions(p.getPresID());
				getSession().save(pm);
			}
			commit();
			return p;
		}
		catch(HibernateException e) {
			rollback();
	        
	        //throw new PersonException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
		return null;
	}
	
	public int getAppointment(int S, int D) throws PException {
		try {
			begin();
			Query q=getSession().createQuery("update Appointmnet set status = 'consulted' where doctor = :D and patient = :S and status = 'pending'");
			q.setInteger("S", S);
			q.setInteger("D", D);
			//List<Patient> persons=new ArrayList<Patient>();
			int result = q.executeUpdate();
			commit();
			return result;
		}
		catch(HibernateException e){
			rollback();
			throw new PException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public int getAppointment2(int S, int D) throws PException {
		try {
			begin();
			Query q=getSession().createQuery("update Appointmnet set status = 'dispatched' where doctor = :D and patient = :S and status = 'consulted'");
			q.setInteger("S", S);
			q.setInteger("D", D);
			//List<Patient> persons=new ArrayList<Patient>();
			int result = q.executeUpdate();
			commit();
			return result;
		}
		catch(HibernateException e){
			rollback();
			throw new PException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<Patient> getPatient(int S) throws PException {
		try {
			begin();
			Query q=getSession().createQuery("from Patient where personId in (select patient from Appointmnet where doctor = :S and status = :D)");
			q.setInteger("S", S);
			q.setString("D", "pending");
			List<Patient> persons=new ArrayList<Patient>();
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
	
	public Prescriptions getPrescription(int p) throws PException {
		try {
			begin();
			Query q=getSession().createQuery("from Prescriptions where id = :p");
			q.setInteger("p", p);
			
			Prescriptions p1 = (Prescriptions)q.uniqueResult();
			commit();
			return p1;
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
