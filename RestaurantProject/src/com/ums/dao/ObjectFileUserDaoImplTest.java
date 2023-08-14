package com.ums.dao;

import java.util.List;

import com.ums.model.Role;
import com.ums.model.User;

import javafx.beans.property.SimpleStringProperty;


public class ObjectFileUserDaoImplTest {
	
	public static void main(String[] args) {
        // Exemple d'utilisation de la classe ObjectFileUserDaoImpl
		// Chemin du fichier de sauvegarde des utilisateurs
        String userFilename = "users.ser";

        // Création du DAO pour les utilisateurs
        ObjectFileUserDaoImpl userDao = new ObjectFileUserDaoImpl(userFilename);

        // Test de la création d'un utilisateur
        User user1 = new User(1, "Najma", "Anushka", "najma.anushka@gmail.com", 
        			"123456789", "najmaLogin", "najmaPassword", Role.ADMIN.getRole());
        try {
            userDao.create(user1);
            System.out.println("Utilisateur créé : " + user1);
        } catch (DAOException e) {
        	System.out.println("UCreate : " + e.getMessage());
        }

        // Test de la lecture d'un utilisateur par son ID
        try {
            User userRead = userDao.read(0);
            System.out.println("Utilisateur lu : " + userRead);
        } catch (DAOException e) {
        	System.out.println("URead : " + e.getMessage());
        }

        // Test de la liste de tous les utilisateurs
        try {
            List<User> userList = userDao.list();
            System.out.println("Liste des utilisateurs :");
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (DAOException e) {
        	System.out.println("UList : " + e.getMessage());
        }

        // Test de la mise à jour d'un utilisateur
        try {
            User userToUpdate = userDao.read(0);
            if (userToUpdate != null) {
                userToUpdate.setNom(new SimpleStringProperty("Jane"));
                userToUpdate.setPrenom(new SimpleStringProperty("Smith"));
                userDao.update(userToUpdate);
                System.out.println("Utilisateur mis à jour : " + userToUpdate);
            }
        } catch (DAOException e) {
        	System.out.println("UMise à jour : " + e.getMessage());
        }

        // Test de la suppression d'un utilisateur
        try {
            userDao.delete(0);
            System.out.println("Utilisateur supprimé");
        } catch (DAOException e) {
        	System.out.println("UDelete : " + e.getMessage());
        }
    }
}
