package com.ums;

import com.ums.dao.UserDaoImpl;

public class UserViewModel {
	 private UserDaoImpl daoImpl;
	 
	 public UserViewModel(UserDaoImpl daoImpl) {
	        this.daoImpl = daoImpl;
	 }
}
