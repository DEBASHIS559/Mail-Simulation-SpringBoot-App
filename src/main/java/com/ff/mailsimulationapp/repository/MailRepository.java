package com.ff.mailsimulationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ff.mailsimulationapp.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Integer>{
	
//	public List<Mail> findByToUserId(int userid);
	
	
	@Query("SELECT m FROM Mail m JOIN m.toUsers u WHERE u.id = :userId")
	public List<Mail> findAllByUserInToUsersId(int userId);
}
