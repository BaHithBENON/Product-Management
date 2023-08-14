package com.ums.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ums.ObjectConverter;
import com.ums.dao.DAOException;
import com.ums.dao.IDao;
import com.ums.db.HibernateConnection;
import com.ums.model.SerializableUser;
import com.ums.model.User;


public class HibernateUserDaoImpl implements IDao<User> {
	
	ObjectConverter objectConverter;
	public HibernateUserDaoImpl() {
		objectConverter = new ObjectConverter();
	}

	@Override
	public void create(User entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			session.save(objectConverter.toSerializableUser(entity));
			// session.refresh(objectConverter.toSerializableUser(entity));
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public User read(int id) throws DAOException {
		// TODO Auto-generated method stub
		User user = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			user = objectConverter.toUser(session.find(SerializableUser.class, id));
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() throws DAOException {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			Query query = session.createQuery("From T_User");
			List<SerializableUser> users_start = query.getResultList();
			
			for (SerializableUser serializableUser : users_start) {
                User user =  objectConverter.toUser(serializableUser);
                users.add(user);
            }
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return users;
	}

	@Override
	public void update(User entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			HibernateConnection.getInstance().closeSession();
			HibernateConnection.getInstance().closeSessionFactory();
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			//SerializableUser sUser = session.get(SerializableUser.class, entity.getId());
			/*
			if (sUser != null) {
			    // L'entité existe dans la session, vous pouvez update
				sUser.setId(entity.getId());
				sUser.setNom(entity.getSimpleNom());
	            sUser.setPrenom(entity.getSimplePrenom());
	            sUser.setTelephone(entity.getSimpleTelephone());
	            sUser.setEmail(entity.getSimpleEmail());
	            sUser.setPassword(entity.getSimplePassword());
	            sUser.setLogin(entity.getSimpleLogin());
	            sUser.setRole(entity.getSimpleRole());
	            
	            session.refresh(sUser);
			}
			*/
			
			session.update(objectConverter.toSerializableUser(entity));
			transaction.commit();
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			User user = read(id);
			if(user != null) {
				SerializableUser sUser = session.get(SerializableUser.class, id);
				if (sUser != null) {
				    // L'entité existe dans la session, vous pouvez la supprimer
				    session.delete(sUser);
				}
				// session.delete(objectConverter.toSerializableUser(user));
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

}
