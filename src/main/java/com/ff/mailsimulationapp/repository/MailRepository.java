package com.ff.mailsimulationapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.util.MailStatus;

public interface MailRepository extends JpaRepository<Mail, Integer>{
	
	@Query("SELECT m FROM Mail m  WHERE m.fromUser= :fromUser AND m.status = :status")
	public List<Mail> findByFromUserAndStatus(User fromUser,MailStatus status);
	
	//used to fetch the data from third table
	@Query("SELECT m FROM Mail m JOIN m.toUsers u WHERE u.id = :userId")
	public List<Mail> findAllByUserInToUsersId(int userId);
	
//	@Query("SELECT m FROM Mail m WHERE m.fromUser= :fromUser AND m.status= :status AND m.id= :mailid")
	public Optional<Mail> findByFromUserAndStatusAndId(User fromUser,MailStatus status,int mailid);
}
