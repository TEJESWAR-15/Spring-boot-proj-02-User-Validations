package com.__app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CitiesMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CITY_ID") 
	private Integer cityId;
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name = "STATE_ID", referencedColumnName = "STATE_ID")
	private  StatesMaster state;
}
