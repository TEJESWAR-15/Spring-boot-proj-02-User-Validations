package com.__app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.__app.Entity.StatesMaster;

@Repository
public interface StateRepo extends JpaRepository<StatesMaster,Integer>{

	List<StatesMaster> findByCountryCountryId(Integer countryId);

}
