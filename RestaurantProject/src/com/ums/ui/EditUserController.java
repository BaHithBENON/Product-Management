package com.ums.ui;

import com.ums.UMSApplication;
import com.ums.dao.DAOException;
import com.ums.model.Role;
import com.ums.model.User;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditUserController {
	
	@FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ComboBox<Role> roleComboBox;
    
    private User user;

	public User getUser() {
		return user;
	}

	public EditUserController() {}
	
	@FXML
    private void initialize() {
		roleComboBox.getItems().clear();
        roleComboBox.getItems().addAll(Role.values());
        if(user == null) 
        	user = new User("", "", "", "", Role.USER);
    }

	public void setUser(User selectedUser) {
		// TODO Auto-generated method stub
		this.user = selectedUser;
        displayUserDetails();
	}
	
	
	private void displayUserDetails() {
        if (user != null) {
            nomTextField.setText(user.getNom().get());
            prenomTextField.setText(user.getPrenom().get());
            emailTextField.setText(user.getEmail().get());
            telephoneTextField.setText(user.getTelephone().get());
            loginTextField.setText(user.getLogin().get());
            passwordTextField.setText(user.getPassword().get());
            
         // Sélectionner le rôle correspondant dans la liste déroulante
            Role userRole = Role.valueOf(user.getRole().get().toUpperCase());
            roleComboBox.getSelectionModel().select(userRole);
        }
    }
	
	// Méthode appelée lorsque l'utilisateur appuie sur le bouton de sauvegarde
    @FXML
    private void saveUser() {
        if (user != null && (
        		!nomTextField.getText().isEmpty() &&
        		!prenomTextField.getText().isEmpty() &&
        		!telephoneTextField.getText().isEmpty() &&
        		!telephoneTextField.getText().isEmpty() &&
        		!loginTextField.getText().isEmpty() &&
        		!passwordTextField.getText().isEmpty() && 
        		!roleComboBox.getSelectionModel().isEmpty()
        )) {
            user.setNom(new SimpleStringProperty(nomTextField.getText()));
            user.setPrenom(new SimpleStringProperty(prenomTextField.getText()));
            user.setEmail(new SimpleStringProperty(emailTextField.getText()));
            user.setTelephone(new SimpleStringProperty(telephoneTextField.getText()));
            user.setLogin(new SimpleStringProperty(loginTextField.getText()));
            user.setPassword(new SimpleStringProperty(passwordTextField.getText()));
            user.setRole(new SimpleStringProperty(roleComboBox.getSelectionModel().getSelectedItem().getRole()));
            
            //
            System.out.println(user.getNom().get());
            
            // UMSApplication.getInstance().saveUser(user);
            // dataSource.getUsers().add(user);
            
            try {
				// UMSApplication.getInstance().getDaoImpl().update(user);
				UMSApplication.getInstance().getHibernateDaoImpl().update(user);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
            // Fermer la fenêtre de modification
            closeWindow();
        } else {
        	//
            System.out.println("Il manque des champs...");
        }
    }
	
	
	// Méthode appelée lorsque l'utilisateur appuie sur le bouton d'annulation
    @FXML
    private void cancelEdit() {
        // Fermer la fenêtre de modification sans sauvegarder les modifications
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) nomTextField.getScene().getWindow();
        stage.close();
    }
}
