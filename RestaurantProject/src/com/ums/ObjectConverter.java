package com.ums;

import com.ums.model.SerializableUser;
import com.ums.model.User;

public class ObjectConverter {
	
	public ObjectConverter() {}
	
	public SerializableUser toSerializableUser(User user) {
    	SerializableUser sUser =  new SerializableUser(
                user.getId(),
                user.getSimpleNom(),
                user.getSimplePrenom(),
                user.getSimpleEmail(),
                user.getSimpleTelephone(),
                user.getSimpleLogin(),
                user.getSimplePassword(),
                user.getSimpleRole()
        );
    	return sUser;
    }

    public User toUser(SerializableUser serializableUser) {
    	User user = new User(serializableUser.getId(), 
    			serializableUser.getNom(), 
    			serializableUser.getPrenom(),
    			serializableUser.getEmail(),
    			serializableUser.getTelephone(),
    			serializableUser.getLogin(),
    			serializableUser.getPassword(),
    			serializableUser.getRole()
    	);
        return user;
    }
    
}
