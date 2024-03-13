package com.ff.mailsimulationapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.mailsimulationapp.dao.UserDao;
import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.exception.MailFailedToSentException;

@Service
public class PasswordService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VerificationTokenService tokenService;

	@Autowired
	private EmailSenderService mailService;

	public ResponseEntity<ResponseStructure<String>> forgotPassword(String email) {

		User user = userDao.getUserByEmail(email);

		if (user != null) {

			String token = tokenService.generateToken(user);

			String body = "Password Recovery Link: \n\nhttp://localhost:8080/password/verify?token=" + token;

			String subject = "Password Recovery Link for" + user.getUserName();

			mailService.sendEmail(user.getRecoveryEmail(), subject, body);

			// give responseStructure stating that email sent successfully.

			ResponseStructure<String> rs = new ResponseStructure<>();

			rs.setData("Recovery Mail Sent Successfully!");
			rs.setMessage("Success");
			rs.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

		} else {

			throw new MailFailedToSentException("User not found with this email!");
		}
	}

	public ResponseEntity<ResponseStructure<String>> verifySecurityQuestion(String verificationToken, String answer) {
		User user = tokenService.getUserByToken(verificationToken);
		if (user != null && user.getAnswer().equalsIgnoreCase(answer)) {

			user.setVerified(true);
			userDao.createUser(user);
			// verification done.
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Success");
			rs.setData("Verification Done! You can now update password!");

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			throw new MailFailedToSentException("Not valid token or answer incorrect!");
		}
	}

	public ResponseEntity<ResponseStructure<String>> updatePassword(String verificationToken, String newPassword) {

		User user = tokenService.getUserByToken(verificationToken);

		if (user != null) {

			if (user.isVerified()) {

				user.setPassword(newPassword);
				user.setVerificationToken(null);
				user.setVerified(false);

				userDao.createUser(user);

				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.OK.value());
				rs.setMessage("Success");
				rs.setData("Password Updated Successfully. You can login now!");

				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);

			} else {
				throw new MailFailedToSentException("Token Not Verification. Verify first!");
			}

		} else {
			throw new MailFailedToSentException("Invalid Verification token!");
		}

	}

}
