package com.ff.mailsimulationapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
