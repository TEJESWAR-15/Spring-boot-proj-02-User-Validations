package com.__app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StatesMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "STATE_ID") 
	private Integer stateId;
	private String stateName;
	
	@ManyToOne
    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID")
	private CountryMaster country;
	
}
