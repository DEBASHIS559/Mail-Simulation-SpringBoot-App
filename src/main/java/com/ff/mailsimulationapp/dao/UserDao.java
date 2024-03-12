package com.ff.mailsimulationapp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.repository.UserRepository;

@Repository
public class UserDao {


	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		
		return userRepository.save(user);
	}
	
	public User findUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public User findUserById(int id) {
		
		return userRepository.findById(id).orElse(null);

	
	}

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
}
