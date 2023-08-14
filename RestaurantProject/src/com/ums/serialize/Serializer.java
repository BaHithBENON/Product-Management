package com.ums.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ums.model.SerializableUser;
import com.ums.model.User;
import com.ums.model.Users;

public class Serializer {
	public Serializer() { }
	
	public void serializeUsers(String userFilename, Users users) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(userFilename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
        }
    }

    public Users deserializeUsers(String userFilename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(userFilename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Users) ois.readObject();
        } catch (FileNotFoundException e) {
            // Fichier non trouvé, créer un nouvel objet Users vide
            return new Users();
        }
    }
    
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
