package com.ums.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ums.model.SerializableUser;
import com.ums.model.User;
import com.ums.model.Users;
import com.ums.serialize.Serializer;


public class ObjectFileUserDaoImpl implements IDao<User> {
	
	Serializer serializer;
	// private static final String SERIALIZATION_FILENAME = "user.ser";
	private String userFilename;

    public String getUserFilename() {
		return userFilename;
	}

	public void setUserFilename(String userFilename) {
		this.userFilename = userFilename;
	}

	public ObjectFileUserDaoImpl(String userFilename) {
        this.userFilename = userFilename;
        serializer = new Serializer();
    }

	@Override
	public void create(User entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
            Users users = serializer.deserializeUsers(userFilename);
            SerializableUser sUser = serializer.toSerializableUser(entity);
            // System.out.println("LLL : " + sUser);
            sUser.setId(generateId());
            users.getUsers().add(sUser);
            serializer.serializeUsers(userFilename, users);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error creating user : " + e);
        }
	}

	@Override
	public User read(int id) throws DAOException {
		// TODO Auto-generated method stub
		try {
            Users users = serializer.deserializeUsers(userFilename);
            
            if(users.getUsers().get(id) != null)
            	return serializer.toUser(users.getUsers().get(id));
            else 
            { 	System.out.println("LLL : Nothing");
            	return new User();
            }
            /*
            for (User user : users.getUsers()) {
                if (user.getId() == id) {
                    return user;
                }
            }
            */
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error reading user : " + e);
        }
        // return null;
	}

	@Override
	public List<User> list() throws DAOException {
		// TODO Auto-generated method stub
		try {
            Users serializableUsers = serializer.deserializeUsers(userFilename);
            List<User> users = new ArrayList<>();
            for (SerializableUser serializableUser : serializableUsers.getUsers()) {
                User user =  serializer.toUser(serializableUser);
                users.add(user);
            }
            return users;
            
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error listing users : " + e);
        }
	}

	@Override
	public void update(User entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
            Users users = serializer.deserializeUsers(userFilename);
            
            int index = users.getUsers().lastIndexOf(serializer.toSerializableUser(entity));
            if(index != -1) {
            	users.getUsers().remove(index);
            	users.getUsers().add(index, serializer.toSerializableUser(entity));
            }
            /*
            for (int i = 0; i < users.getUsers().size(); i++) {
                User user = users.getUsers().get(i);
                if (user.getId() == entity.getId()) {
                    // Mettre à jour les propriétés avec les nouvelles valeurs
                    user.getNom().set(entity.getNom().get());
                    user.getPrenom().set(entity.getPrenom().get());
                    user.getEmail().set(entity.getEmail().get());
                    user.getTelephone().set(entity.getTelephone().get());
                    user.getLogin().set(entity.getLogin().get());
                    user.getPassword().set(entity.getPassword().get());
                    user.getRole().set(entity.getRole().get());
                    users.getUsers().set(i, user);
                    break;
                }
            }
            */
            serializer.serializeUsers(userFilename, users);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error updating user : " + e);
        }
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		try {
            Users users = serializer.deserializeUsers(userFilename);
            users.getUsers().removeIf(user -> user.getId() == id);
            
            /*
            User entity = users.getUsers().get(id);
            if(entity != null) {
            	users.getUsers().remove(id);
            }
            */
            
            serializer.serializeUsers(userFilename, users);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error deleting user : " + e);
        }
	}
	
	private int generateId() throws IOException, ClassNotFoundException {
        Users users = serializer.deserializeUsers(userFilename);
         
        int maxId = 0;
        /*
        for (User user : users.getUsers()) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }*/
        for (SerializableUser user : users.getUsers()) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;
    }

}

