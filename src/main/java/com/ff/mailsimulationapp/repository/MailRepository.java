package com.ff.mailsimulationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.mailsimulationapp.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Integer>{

}