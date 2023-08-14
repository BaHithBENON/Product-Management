package com.ums.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ums.db.HibernateConnection;
import com.ums.db.UMSDBException;
import com.ums.model.User;

import javax.persistence.Query;

public class HibernateUserDaoImplPrime implements IUserDao<User> {

	@Override
	public void create(User entity) throws UMSDBException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			session.save(entity);
			
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public User read(int id) throws UMSDBException {
		// TODO Auto-generated method stub
		User user = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			user = session.find(User.class, id);
			
		} catch (Exception e) {
			throw new UMSDBException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() throws UMSDBException {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			Query query = session.createQuery("From User");
			users = query.getResultList();
		} catch (Exception e) {
			throw new UMSDBException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return users;
	}

	@Override
	public void update(User entity) throws UMSDBException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			session.update(entity);
			
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) throws UMSDBException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			User user = read(id);
			if(user != null)
				session.delete(user);
			
			transaction.commit();
		} catch (Exception e) {
			throw new UMSDBException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

}

