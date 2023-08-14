package com.ums.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity(name="T_User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty email;
	private StringProperty telephone;
	private StringProperty login;
	private StringProperty password;
	private StringProperty role;
	
	public User() { }

	
	public User(int id, String nom, String prenom, String email, String telephone, String login, String password, String role) {
		this.id = id;
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.email = new SimpleStringProperty(email);
		this.telephone = new SimpleStringProperty(telephone);
		// login et password ...
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.role = new SimpleStringProperty(role);
	}
	
	
	/*
	public User(int id, String nom, String prenom, String email, String telephone, String login, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        // login et password par défaut ...
        this.login = login;
        this.password = password;
        this.role = role;
        this.id = id;
    }
    */
	
	
	public User(String nom, String prenom, String email, String telephone, Role role) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.email = new SimpleStringProperty(email);
		this.telephone = new SimpleStringProperty(telephone);
		// login et password par défaut ...
		this.login = new SimpleStringProperty(prenom.trim().toLowerCase() + "." +
		nom.trim().toLowerCase());
		this.password = new SimpleStringProperty("p@Ss3R");
		this.role = new SimpleStringProperty(role.getRole());
	}
	
	/*
	public User(String nom, String prenom, String email, String telephone, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        // login et password par défaut ...
        this.login = prenom.trim().toLowerCase() + "." +
        		nom.trim().toLowerCase();
        this.password = "p@Ss3R";
        this.role = role.getRole();
    }
	*/
	
	
	public User(int identifiant, String login, String password) {
		this.id = identifiant;
		this.password = new SimpleStringProperty(password);
		this.login = new SimpleStringProperty(login);
	}
	
	/*
	public User(int identifiant, String login, String password) {
		this.id = identifiant;
		this.password = password;
		this.login = login;
	}
	*/
	

	public User(String login, String password) {
		this.password = new SimpleStringProperty(password);
		this.login = new SimpleStringProperty(login);
	}
	
	
	/*
	public User(String login, String password) {
		this.password = password;
		this.login = login;
	}
	*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public StringProperty getNom() {
		return nom;
	}
	
	public String getSimpleNom() {
		return nom.get();
	}

	public void setNom(StringProperty nom) {
		this.nom = nom;
	}

	public StringProperty getPrenom() {
		return prenom;
	}
	
	public String getSimplePrenom() {
		return prenom.get();
	}

	public void setPrenom(StringProperty prenom) {
		this.prenom = prenom;
	}

	public StringProperty getEmail() {
		return email;
	}
	
	public String getSimpleEmail() {
		return email.get();
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getTelephone() {
		return telephone;
	}
	
	public String getSimpleTelephone() {
		return telephone.get();
	}

	public void setTelephone(StringProperty telephone) {
		this.telephone = telephone;
	}

	public StringProperty getLogin() {
		return login;
	}
	
	public String getSimpleLogin() {
		return login.get();
	}

	public void setLogin(StringProperty login) {
		this.login = login;
	}

	public StringProperty getPassword() {
		return password;
	}
	
	public String getSimplePassword() {
		return password.get();
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}

	public StringProperty getRole() {
		return role;
	}
	
	public String getSimpleRole() {
		return role.get();
	}

	public void setRole(StringProperty role) {
		this.role = role;
	}
	
	
	
	/*
	public String getNom() {
        return nom;
    }

    public StringProperty getNomProperty() {
        return new SimpleStringProperty(nom);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public StringProperty getPrenomProperty() {
        return new SimpleStringProperty(prenom);
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public StringProperty getEmailProperty() {
        return new SimpleStringProperty(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public StringProperty getTelephoneProperty() {
        return new SimpleStringProperty(telephone);
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public StringProperty getLoginProperty() {
        return new SimpleStringProperty(login);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public StringProperty getPasswordProperty() {
        return new SimpleStringProperty(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public StringProperty getRoleProperty() {
        return new SimpleStringProperty(role);
    }

    public void setRole(String role) {
        this.role = role;
    }
    */
    
    
	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom=" + nom +
                ", prenom=" + prenom +
                ", email=" + email +
                ", telephone=" + telephone +
                ", login=" + login +
                ", password=" + password +
                ", role=" + role +
                '}';
    }
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(!(obj instanceof User)) return false;
		
		if(this.id == ((User)obj).getId())
			return true;
		
		return false;
	}

}
