package com.ums;

import com.ums.model.Role;
import com.ums.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {
	
	private ObservableList<User> users = FXCollections.observableArrayList();
	
	public DataSource() {
		// users.add(new User("Dior", "Ndeye", "ndeye.dior@gmail.com", "784392976", Role.USER));
		// users.add(new User("FK", "B", "fkb@gmail.com", "784392976", Role.ADMIN));
		users.add(new User("Dior", "Ndeye", "ndeye.dior@gmail.com", "775231245", Role.USER));
		users.add(new User("Yaffa", "Mouhamed", "mouhamed.yaffa@gmail.com", "773391555", Role.USER));
		users.add(new User("Djibril", "Sow", "djibril.sow@gmail.com", "783212055", Role.ADMIN));
		users.add(new User("Seydina", "Fall", "seydina.fall@gmail.com", "705213689", Role.USER));
		users.add(new User("Kona", "Chafaa", "Chafaa.kona@gmail.com", "773251007", Role.ADMIN));
		users.add(new User("Tall", "Yacine", "yacine.tall@gmail.com", "780157896", Role.USER));
		users.add(new User("SAME", "Ridwane", "ridwane.same@gmail.com", "761250067", Role.ADMIN));
		users.add(new User("SEME", "Mamdou", "mamadou.seme@gmail.com", "77636700", Role.ADMIN));
		users.add(new User("Dieng", "Mouhamed", "mouhamed.dieng@gmail.com", "708961209", Role.USER));
		users.add(new User("Bahini", "Freddy", "freddy.bahini@gmail.com", "783361728", Role.USER));
	}
	
	public ObservableList<User> getUsers() {
		return users;
	}
	
	public void createUser(User user) {
		users.add(user);
	}
}
