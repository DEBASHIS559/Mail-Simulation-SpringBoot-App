package com.ff.mailsimulationapp.dao;

import java.util.Optional;

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
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
}
