package com.ums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ums.datas.DBManager;
import com.ums.model.User;

public class UserDaoImpl implements IDao<User> {
	@Override
	public void create(User entity) throws DAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Insert into T_Users (nom, prenom, email, telephone, login, password, role) values (?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, entity.getNom().get());
			ps.setString(2, entity.getPrenom().get());
			ps.setString(3, entity.getEmail().get());
			ps.setString(4, entity.getTelephone().get());
			ps.setString(5, entity.getLogin().get());
			ps.setString(6, entity.getPassword().get());
			ps.setString(7, entity.getRole().get());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public User read(int id) throws DAOException { 
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From T_Users where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String login = rs.getString("login");
				String password = rs.getString("password");
				User user = new User(id, login, password);
				return user;
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<User> list() throws DAOException { 
		List<User> users = new ArrayList<>();
		
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From T_Users";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("id");
	            String nom = rs.getString("nom");
	            String prenom = rs.getString("prenom");
	            String email = rs.getString("email");
	            String telephone = rs.getString("telephone");
	            String login = rs.getString("login");
	            String password = rs.getString("password");
	            String role = rs.getString("role");


				User user = new User (id, nom, prenom, email, telephone, login, password, role);
				user.getEmail().set(email);
				user.getNom().set(nom);
				user.getPrenom().set(prenom);
				user.getTelephone().set(telephone);
				user.getRole().set(role);
				
				users.add(user);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return users;
	}
	
	@Override
	public void update(User entity) throws DAOException {
		try (Connection connection = DBManager.getConnection()) {
			 StringBuilder queryBuilder = new StringBuilder("UPDATE T_Users SET ");
		        queryBuilder.append("login=?, password=?, nom=?, prenom=?, email=?, telephone=?, role=? ");
		        queryBuilder.append("WHERE id=?");
		        
		        PreparedStatement ps = connection.prepareStatement(queryBuilder.toString());
		        ps.setString(1, entity.getLogin().get());
		        ps.setString(2, entity.getPassword().get());
		        ps.setString(3, entity.getNom().get());
		        ps.setString(4, entity.getPrenom().get());
		        ps.setString(5, entity.getEmail().get());
		        ps.setString(6, entity.getTelephone().get());
		        ps.setString(7, entity.getRole().get());
		        ps.setInt(8, entity.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public void delete(int id) throws DAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Delete From T_Users Where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	public void delete(String nom) throws DAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Delete From T_Users Where nom=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nom);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
}
