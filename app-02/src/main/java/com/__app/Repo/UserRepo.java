package com.__app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__app.Entity.UserMaster;

@Repository
public interface UserRepo extends JpaRepository<UserMaster,Integer>{

	UserMaster findByEmailAndPassword(String email,String pwd);

	UserMaster findByEmail(String email);
	
}
