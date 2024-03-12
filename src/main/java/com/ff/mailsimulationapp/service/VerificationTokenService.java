package com.ff.mailsimulationapp.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ff.mailsimulationapp.dao.UserDao;
import com.ff.mailsimulationapp.entity.User;

@Service
public class VerificationTokenService {
	
	
	@Autowired
	private UserDao userDao;
	
	public String generateToken(User user) {
		
		
		String verificationToken = UUID.randomUUID().toString();
		
		user.setVerificationToken(verificationToken);
		userDao.createUser(user);
		
		return verificationToken;
	}
	
	
	public User getUserByToken(String verificationToken) {
		
		return userDao.findByVerificationToken(verificationToken);
	}
	
	
	public void invalidateToken(String verificationToken) {
		
	}

}
