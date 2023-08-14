package com.ums;

import java.io.IOException;

import com.ums.dao.UserDaoImpl;
import com.ums.db.dao.HibernateUserDaoImpl;
import com.ums.model.User;
import com.ums.ui.EditUserController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UMSApplication extends Application {

	private Stage primaryStage;
	private BorderPane mainUI;
	private AnchorPane userUI;
	
	private UserDaoImpl daoImpl;
	private HibernateUserDaoImpl hDaoImpl;
	
	private static UMSApplication instance = null;
	
	public static UMSApplication getInstance() {
		return instance;
	}
	
	public UserDaoImpl getDaoImpl() {
		if(daoImpl != null)
			return daoImpl;
		else {
			daoImpl = new UserDaoImpl();
		}
		return daoImpl;
	}
	
	public HibernateUserDaoImpl getHibernateDaoImpl() {
		if(hDaoImpl != null)
			return hDaoImpl;
		else {
			hDaoImpl = new HibernateUserDaoImpl();
		}
		return hDaoImpl;
	}

	@Override
	public void start(Stage primaryStage) {
		
		instance = this;
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("User Management System");
		
		initRootLayout();
		showUserUI();
	}
	
	// Initializes the root layout.
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			mainUI = (BorderPane) FXMLLoader.load(getClass().getResource("ui/MainUI.fxml"));
			// Show the scene containing the root layout.
			Scene scene = new Scene(mainUI);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	// Shows the user UI inside the root layout.
	public void showUserUI() {
		try {
			// Load userUI
			userUI = (AnchorPane) FXMLLoader.load(getClass().getResource("ui/UserUI.fxml"));
			// Set userUI into the center of root layout.
			mainUI.setCenter(userUI);
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	private EditUserController editUserController;
	public EditUserController getEditUserController() {
		return editUserController;
	}
	
	// Shows the user UI inside the root layout.
	public void showEditUserUI(User user) {
		// Ouvrir une nouvelle fenêtre de modification de l'utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/EditUser.fxml"));
        Parent root;
        try {
            root = loader.load();
            editUserController = loader.getController();
            // UserUIController userUIController = new FXMLLoader(getClass().getResource("ui/UserUI.fxml")).getController();
            
            // Passer l'utilisateur sélectionné au contrôleur de la fenêtre de modification
            editUserController.setUser(user);
            
            // Créer une nouvelle scène pour la fenêtre de modification
            Scene scene = new Scene(root);
            
            // Créer une nouvelle fenêtre pour la scène de modification
            Stage stage = new Stage();
            stage.setTitle("Modifier l'utilisateur");
            stage.setScene(scene);
            
            // Bloquer les interactions avec la fenêtre principale tant que la fenêtre de modification est ouverte
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            
            // Afficher la fenêtre de modification
            stage.showAndWait();
            
            // Mettre à jour les données du tableau après la fermeture de la fenêtre de modification
            //userTable.refresh();
            //userUIController.refreshTable();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/*
	public void saveUser(User user) {
		UserUIController userUIController = new FXMLLoader(getClass().getResource("ui/UserUI.fxml")).getController();
		userUIController.saveUser(user);
	}
	*/
	
	// Returns the main stage.
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
