package com.ums.dao;

import com.ums.model.User;

import java.util.List;

public class UserDaoImplTest {

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        // Test de la méthode create
        User user1 = new User("Anushka Najma", "anushka@password");
        try {
            userDao.create(user1);
            System.out.println("Utilisateur créé avec succès.");
        } catch (DAOException e) {
            System.out.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
        }
        
     // Test de la méthode list
        try {
            List<User> userList = userDao.list();
            System.out.println("Liste des utilisateurs :");
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (DAOException e) {
            System.out.println("Erreur lors de la récupération de la liste des utilisateurs : " + e.getMessage());
        }

        // Test de la méthode read
        try {
        	/*
        	if(user1.getId() == 0) {
        		user1.setId(2);
        	}
        	*/
            User retrievedUser = userDao.read(1);
            System.out.println("Utilisateur récupéré : " + retrievedUser);
        } catch (DAOException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }

        

        // Test de la méthode update
        User userToUpdate = new User(user1.getId(), "updated.login", "updated.password");
        try {
            userDao.update(userToUpdate);
            System.out.println("Utilisateur mis à jour avec succès.");
        } catch (DAOException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }

        // Test de la méthode delete
        try {
            userDao.delete(user1.getId());
            System.out.println("Utilisateur supprimé avec succès.");
        } catch (DAOException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }
}
