package com.ums.ui;

import java.io.IOException;
import java.util.List;

import com.ums.UMSApplication;
import com.ums.dao.DAOException;
import com.ums.model.SerializableUser;
import com.ums.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserUIController {
	
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> nomColumn;
	@FXML
	private TableColumn<User, String> prenomColumn;
	
	@FXML
	private Label nomLabel;
	@FXML
	private Label prenomLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label telephoneLabel;
	@FXML
	private Label loginLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private Label roleLabel;
    @FXML
    private TextField searchTextField;
    @FXML
    private FilteredList<User> filteredData;
    private ObservableList<User> users = FXCollections.observableArrayList();

	// private DataSource dataSource;
	
	// private UserDaoImpl daoImpl;

	public UserUIController() { }
	
	
	private void updateUsersTable() {
		try {
			// List<User> usersList = UMSApplication.getInstance().getDaoImpl().list();
			List<User> usersList = UMSApplication.getInstance().getHibernateDaoImpl().list();
			/*
			for(User u : usersList) {
				System.out.println(u.getLogin().get());
			}
			*/
			filteredData = new FilteredList<>(FXCollections.observableList(usersList));
			userTable.setItems(filteredData);
			
			// filteredData = new FilteredList<>(dataSource.getUsers(), p -> true);
			// userTable.setItems(filteredData);
			// userTable.setItems(dataSource.getUsers());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void initialize() {
		// dataSource = new DataSource();
		
		// daoImpl = new UserDaoImpl();
		
		updateUsersTable();
		
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenom());
		
		
		// Ajouter un écouteur pour la sélection d'un utilisateur dans le tableau
	    userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    	selectUser(newValue);
	    });
	    
	    
	    searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
	        // Appeler votre fonction ici
	        // La nouvelle valeur du texte saisi est disponible dans newValue
	        // Vous pouvez effectuer les actions nécessaires en fonction de cette valeur
	    	searchUsers(newValue);
	    });
	    
	    //String initialSearchText = searchTextField.getText();
	    //searchUsers(initialSearchText);

	}
	
	private void searchUsers(String keyword) {
		
	    filteredData.setPredicate(user -> {
	        if (keyword == null || keyword.isEmpty()) {
	            // Afficher tous les utilisateurs si la recherche est vide
	            return true;
	        }
	        
	        String[] searchTerms = keyword.split("\\s+"); // Divise la chaîne de recherche en termes séparés par des espaces

	        for(String term : searchTerms) {
	        	String lowercaseKeyword = term.toLowerCase();
	        	// Filtrer les utilisateurs en fonction du mot-clé de recherche
	        	if (user.getLogin().get().toLowerCase().contains(lowercaseKeyword)) {
	        		return true;
	        	} else if (user.getNom().get().toLowerCase().contains(lowercaseKeyword)) {
	        		return true;
	        	} else if (user.getPrenom().get().toLowerCase().contains(lowercaseKeyword)) {
	        		return true;
	        	}
	        }
	        
	        // Masquer les utilisateurs qui ne correspondent pas à la recherche
	        return false;
	    });
	}

	
	@FXML
	private void selectUser(User selectedUser) {
	    // selectedUser = userTable.getSelectionModel().getSelectedItem();
	    if (selectedUser != null) {
	        // Mettre à jour les étiquettes avec les informations de l'utilisateur sélectionné
	    	// Lier les propriétés de l'utilisateur aux labels
            nomLabel.textProperty().bind(selectedUser.getNom());
            prenomLabel.textProperty().bind(selectedUser.getPrenom());
            emailLabel.textProperty().bind(selectedUser.getEmail());
            telephoneLabel.textProperty().bind(selectedUser.getTelephone());
            loginLabel.textProperty().bind(selectedUser.getLogin());
            passwordLabel.textProperty().bind(selectedUser.getPassword());
            roleLabel.textProperty().bind(selectedUser.getRole());
	    } else {
	    	// Si aucun utilisateur n'est sélectionné, réinitialiser les labels
            nomLabel.textProperty().unbind();
            prenomLabel.textProperty().unbind();
            emailLabel.textProperty().unbind();
            telephoneLabel.textProperty().unbind();
            loginLabel.textProperty().unbind();
            passwordLabel.textProperty().unbind();
            roleLabel.textProperty().unbind();
            
            nomLabel.setText("");
            prenomLabel.setText("");
            emailLabel.setText("");
            telephoneLabel.setText("");
            loginLabel.setText("");
            passwordLabel.setText("");
            roleLabel.setText("");
	    }
	}
	
	@FXML
    private void createUser() {
		/*
        // Créer un nouvel utilisateur et l'ajouter à la liste
        User newUser = new User("", "", "", "", Role.USER);
        dataSource.getUsers().add(newUser);

        // Sélectionner le nouvel utilisateur dans le tableau
        userTable.getSelectionModel().select(newUser);
        userTable.refresh();
        */
        
     // Ouvrir une nouvelle fenêtre de modification de l'utilisateur
        //UMSApplication.getInstance().showEditUserUI(newUser);
        
     // Ouvrir une nouvelle fenêtre de modification de l'utilisateur
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUser.fxml"));
        	Parent root;
            root = loader.load();
            EditUserController editUserController = loader.getController();
            // UserUIController userUIController = new FXMLLoader(getClass().getResource("ui/UserUI.fxml")).getController();
            
            
            // Créer une nouvelle scène pour la fenêtre de modification
            Scene scene = new Scene(root);
            
            // Créer une nouvelle fenêtre pour la scène de modification
            Stage stage = new Stage();
            stage.setTitle("Modifier l'utilisateur");
            stage.setScene(scene);
            
            // Bloquer les interactions avec la fenêtre principale tant que la fenêtre de modification est ouverte
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(primaryStage);
            //
            // Afficher la fenêtre de modification
            stage.showAndWait();
            
            User newUser = editUserController.getUser();
            
            if(newUser != null) {
            	
            	userTable.refresh();
            	// Ajouter le nouvel utilisateur à la source de données
                // dataSource.getUsers().add(newUser);
                try {
                	// UMSApplication.getInstance().getDaoImpl().create(newUser);
                	UMSApplication.getInstance().getHibernateDaoImpl().create(newUser);
					
					updateUsersTable();
					
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                // Sélectionner le nouvel utilisateur dans la TableView
                userTable.getSelectionModel().select(newUser);

                // Rafraîchir la TableView
                userTable.refresh();
            }
            // Mettre à jour les données du tableau après la fermeture de la fenêtre de modification
            //userTable.refresh();
            //userUIController.refreshTable();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
	public void refreshTable() {
		userTable.refresh();
	}
	
	public void saveUser(User user) {
		// dataSource.createUser(user);
		userTable.refresh();
	}
	
	@FXML
	private void editUser() {
	    // Récupérer l'utilisateur sélectionné dans le tableau
	    User selectedUser = userTable.getSelectionModel().getSelectedItem();
	    
	    if (selectedUser != null) {
	    	UMSApplication.getInstance().showEditUserUI(selectedUser);
	    }
	    userTable.refresh();
	    updateUsersTable();
	}
	
	@FXML
	private void updateUser(ActionEvent event) {
	    User selectedUser = userTable.getSelectionModel().getSelectedItem();
	    if (selectedUser != null) {
	        // Mettre à jour les informations de l'utilisateur avec les nouvelles valeurs des étiquettes
	        //selectedUser.setNom(nomLabel.getText());
	        //selectedUser.setPrenom(prenomLabel.getText());
	        // ...
	        // Rafraîchir la table des utilisateurs
	        userTable.refresh();
	        updateUsersTable();
	    }
	}
	
	@FXML
	private void deleteUser(ActionEvent event) {
	    User selectedUser = userTable.getSelectionModel().getSelectedItem();
	    System.out.println(selectedUser.getId());
	    if (selectedUser != null) {
	        // Supprimer l'utilisateur sélectionné de la liste des utilisateurs dans DataSource
	        // Rafraîchir la table des utilisateurs
	    	/*
	        userTable.getItems().remove(selectedUser);
	        userTable.refresh();
	        */
	        
	        try {
	        	// UMSApplication.getInstance().getDaoImpl().delete(selectedUser.getId());
	        	UMSApplication.getInstance().getHibernateDaoImpl().delete(selectedUser.getId());
				userTable.refresh();
				updateUsersTable();
				userTable.getSelectionModel().clearSelection();
				// Sélectionner automatiquement le premier élément
				userTable.getSelectionModel().selectFirst(); 
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

	

}
