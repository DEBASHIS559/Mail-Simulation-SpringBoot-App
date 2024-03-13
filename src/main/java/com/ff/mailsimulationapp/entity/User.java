package com.ff.mailsimulationapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String userName;

	@Column(unique = true)
	private String email;

	@Schema(hidden = true)
	@Column(nullable = false)
	private String password;

	private String verificationToken;

	private String question;

	private String answer;

	private boolean isVerified;

	private String recoveryEmail;

}
