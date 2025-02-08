package com.__app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__app.Entity.CitiesMaster;

@Repository
public interface CityRepo extends JpaRepository<CitiesMaster,Integer>{

	List<CitiesMaster> findByStateStateId(Integer stateId);

}
