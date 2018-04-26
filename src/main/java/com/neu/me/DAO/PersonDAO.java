package com.neu.me.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.me.Exception.PException;
import com.neu.me.pojo.Patient;
import com.neu.me.pojo.person;
public class PersonDAO extends DAO{
	public int getAllPerson(String userName)throws PException{
		try{
			begin();
			Query q=getSession().createQuery("from person");
			List<person> persons=new ArrayList<person>();
			persons=q.list();
			commit();
			for(person A:persons){
				if(userName.equals(A.getUserName())){
					return A.getPersonId();
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
		return -1;
		}
	public person getPerson(String userName)throws PException{
		try{
			begin();
			Query q=getSession().createQuery("from person");
			List<person> persons=new ArrayList<person>();
			persons=q.list();
			commit();
			for(person A:persons){
				if(userName.equals(A.getUserName())){
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

	public person create(String name, String userName, String password,String email, String type) throws PException {
		try {
			begin();
			System.out.println(name);
			Patient person = new Patient();
			person.setName(name);
			person.setUserName(userName);
			person.setType(type);
			person.setEmail(email);
			person.setPwd(String.valueOf(password));
			getSession().save(person);
			
			commit();
			
			return person;
		}
		catch(HibernateException e) {
			rollback();
           
            throw new PException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
}
