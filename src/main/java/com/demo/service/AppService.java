package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AppDao;
import com.demo.model.User;

@Service
public class AppService {
	@Autowired
	AppDao appDao;

	public User getUserInfo(String userId) {
		// TODO Auto-generated method stub
		return appDao.getUserInfo(userId);
	}

	public User saveUserInfo(User user) {
		// TODO Auto-generated method stub
		return appDao.saveUserInfo(user);
	}

	public boolean updateUserInfo(User user, String userId) {
		// TODO Auto-generated method stub
		return appDao.updateUserInfo(user,userId);
	}

	public boolean deleteUserInfo(String userId) {
		// TODO Auto-generated method stub
		return appDao.deleteUserInfo(userId);
	}

}
