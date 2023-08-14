package com.ums.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SerializableUser> serializableUser;
	
	
	/*
	public List<User> getUsers() {
		return users;
	}
	*/
	
	public List<SerializableUser> getUsers() {
		return serializableUser;
	}

	/*
	public void setUsers(List<User> users) {
		this.users = users;
	}
	*/
	
	public void setUsers(List<SerializableUser> users) {
		this.serializableUser = users;
	}


	public Users() {
		// users = new ArrayList<>();
		serializableUser = new ArrayList<>();
	}
	
	/*
	
	
	public void serialiserUser (String userFilename, Users users) throws IOException {
		
		try (FileOutputStream fos = new FileOutputStream(userFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(users);
		}
		
	}
	
	public Users deserialiserUsers (String userFilename) throws IOException, ClassNotFoundException {
		
		try (FileInputStream fis = new FileInputStream(userFilename);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			return (Users) ois.readObject();
		}
		
	}
	
	*/


}
