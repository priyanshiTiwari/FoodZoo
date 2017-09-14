package com.avizva.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.avizva.Model.Users;
import com.avizva.service.ServiceDAOImpl;

@Repository
public class UserDAOImpl implements UserDAO {
	Logger logger=Logger.getLogger(ServiceDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}
	public boolean saveUser(Users user) {
       logger.info("------inside dao:saveuser method------");
		boolean flag = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();

			System.out.println(user);
			user.setEnabled(true);
			session.save(user);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			logger.error("exception occured:"+ e);
			transaction.rollback();
			
		} finally {
			session.close();
		}

		return flag;

	}
	public boolean updateUser(String username) {

		boolean flag = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			Users user = session.get(Users.class, username);
			session.update(user);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			logger.error("exception occured:"+ e);
			transaction.rollback();
		} finally {
			session.close();

		}
		return flag;
	}

	
	public boolean valid(String username, String password) {
		logger.info("------entered into userdao:valid method-----");
		
		boolean flag = false;
		Session session = null;
		Transaction transaction = null;
		try
		{
		session = getSession();
		transaction = session.beginTransaction();
		Query query = session.createQuery("from Users where username =:username AND password =:password ");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<Users> list = query.list();
		if (list.isEmpty()) {

			logger.info("----query result empty----");
			flag = false;
		}
		else{
			
			flag=true;}
		}
		catch(Exception e){
			logger.error("exception occured:"+ e);
			transaction.rollback();
		}

		finally
		{
		session.close();
		}
       
		return flag;

	}

	
	public boolean deactivateUser(String username) {
		boolean flag=false;
		Session session=null;
		Transaction transaction =null;
		logger.info("Inside Deactivate UserDAOIMPl "+username);
		try{
		session = getSession();
		transaction = session.beginTransaction();
		Users user = session.get(Users.class, username);
		user.setEnabled(false);
		logger.info("Inside Deactivate UserDAOIMPl. Getting user "+user);
		session.update(user);
		transaction.commit();
		flag = true;
		}
		catch(Exception e){
			logger.error("exception occured:"+ e);
			transaction.rollback();
		}
		finally{
			session.close();
			
		}
		return flag;
	}


	public Users viewUser(String username) {
		boolean flag = false;
		Session session = null;
		Transaction transaction = null;
		session = getSession();
		transaction = session.beginTransaction();
		Users user=session.get(Users.class, username);
	     
		session.close();
		return user;
	
	}
	public String securityque(String username)
	{
		
		String flag=null;
		Session session=null;
		Transaction transaction =null;
		try{
		session = getSession();
		transaction = session.beginTransaction();
		Query q =  session.createQuery("select securityque from Users where username=:username");
		 q.setParameter("username", username);
		List<String> securityLlist = q.list();
		
		flag=securityLlist.get(0);
		logger.info("security question is:" + flag);
		transaction.commit();
	
		}
		catch(Exception e){
			logger.error("exception occured:"+ e);
			transaction.rollback();
		}
		finally{
			session.close();
			
		}
		return flag;
		
		
	}
	public boolean securityans(String answer,String username)
	{
		String result=null;
		boolean flag=false;
		Session session=null;
		Transaction transaction =null;
		try{
		session = getSession();
		transaction = session.beginTransaction();
		Query q =  session.createQuery("select securityans from Users where username=:username");
		 q.setParameter("username", username);
		List<String> securityLlist = q.list();
		
		result=securityLlist.get(0);
		logger.info("security answer: " + flag);
		if(result.equalsIgnoreCase(answer))
		{
			flag=true;
		
		transaction.commit();
		}
		}
		catch(Exception e){
			logger.error("exception occured:"+ e);
			transaction.rollback();
		}
		finally{
			session.close();
			
		}
		return flag;
		
	}
	
	
	public boolean updatepassword(String password,String username)
	{
		System.out.println(password+" "+username);
		String result=null;
		boolean flag=false;
		Session session=null;
		Transaction transaction =null;
		try{
		session = getSession();
		transaction = session.beginTransaction();
		
		Query q =  session.createQuery("UPDATE Users set password =:password WHERE username =:username");
		 q.setParameter("username", username);
		 q.setParameter("password", password);
		int result1= q.executeUpdate();
		flag=true;
		System.out.println("password updated"+ result1);
		transaction.commit();
		}
		catch(Exception e){
			logger.error("exception occured:"+ e);
			transaction.rollback();
		}
		finally{
			session.close();
			
		}
		return flag;
		
	}

}
