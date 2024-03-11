package com.ff.mailsimulationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.mailsimulationapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
