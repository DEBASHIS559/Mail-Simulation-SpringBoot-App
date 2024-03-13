package com.ff.mailsimulationapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.mailsimulationapp.dao.UserDao;
import com.ff.mailsimulationapp.dto.ResponseStructure;

import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.exception.MailFailedToSentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> createUser(User user){
		User receivedUser = userDao.createUser(user);
		
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setMessage("Successfully created!");
		rs.setData(receivedUser);
		rs.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<User>> login(String email, String password, HttpServletRequest request){
		User receivedUser = userDao.findUser(email,password);
		
		
		if(receivedUser != null) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("username", receivedUser.getUserName());
			
			ResponseStructure<User> rs = new ResponseStructure<User>();
			rs.setMessage("Successfully logged in!");
			rs.setData(receivedUser);
			rs.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		}
		else {
			throw new MailFailedToSentException("User Authentication Failed");
		}
		
	}

	public ResponseEntity<ResponseStructure<User>> findUserById(int id){   //if required further
		User receivedUser = userDao.findUserById(id);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setMessage("Successfully logged in!");
		rs.setData(receivedUser);
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		
	}
}
