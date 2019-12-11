package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.AppUserDAO;
import com.example.demo.Entities.AppUser;

@Service
public class AppUserService {
	@Autowired
	private AppUserDAO appUserDao;
	
	public AppUser findAppUser(String userName) {
		return appUserDao.findUserAccount(userName);
	}
}
