package com.__app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__app.entity.Counsellor;
import java.util.List;


@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor,Integer>{
	
	Counsellor findByEmail(String email);

}
