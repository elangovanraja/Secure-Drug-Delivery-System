package com.neu.me.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.me.Exception.PException;
import com.neu.me.pojo.Appointmnet;
public class AppointmentDAO extends DAO {

	public  List<Appointmnet> getAppoints(){
		try {
			begin();
			Query q=getSession().createQuery("from Appointmnet");
			List<Appointmnet> appoints=new ArrayList<Appointmnet>();
			appoints=q.list();
			commit();
			return appoints;
		}
		catch(HibernateException e){
			rollback();
		//	throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
		return null;
	}
	public Appointmnet createAppointment(int doctor, int patient) throws PException {
		try {
			begin();
			Appointmnet appointment = new Appointmnet();
			appointment.setDoctor(doctor);
			appointment.setPatient(patient);
			appointment.setStatus("pending");
			getSession().save(appointment);
			commit();
			return appointment;
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
