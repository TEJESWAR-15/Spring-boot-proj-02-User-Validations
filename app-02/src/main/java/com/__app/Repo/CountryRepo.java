package com.__app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__app.Entity.CountryMaster;

@Repository
public interface CountryRepo extends JpaRepository<CountryMaster,Integer>{

}
