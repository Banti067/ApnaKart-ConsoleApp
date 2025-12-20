package com.apnakart.service;

import com.apnakart.daodata.UserData;
import com.apnakart.model.UsersModel;

public class UserService {

	private UserData userData = new UserData();

	// USer Register Service
	public boolean registerService(UsersModel user) {
		return userData.userRegisterDAO(user);
	}

	//User Login service
	public UsersModel loginService(String email, String password) {
		return userData.doLoginDAO(email, password);
	}
}
