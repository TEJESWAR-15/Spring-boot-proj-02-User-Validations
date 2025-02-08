package com.__app.Entity;

import java.time.LocalDateTime;

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
public class UserMaster {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer userId;
	
	    private String username;

	    @Column(name = "EMAIL")
	    private String email;

	    @Column(name = "PHNO")
	    private Long phone;

	    @Column(name = "PWD")
	    private String password;

	    @Column(name = "PWD_UPDATED")
	    private String passwordUpdated;

	    @ManyToOne
	    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID")
	    private CountryMaster country;

	    @ManyToOne
	    @JoinColumn(name = "STATE_ID", referencedColumnName = "STATE_ID")
	    private StatesMaster state;

	    @ManyToOne
	    @JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID")
	    private CitiesMaster city;

	    @Column(name = "PWD_RESET")
	    private String passwordReset;

	    @Column(name = "CREATED_DATE")
	    private LocalDateTime createdDate;

	    @Column(name = "UPDATED_DATE")
	    private LocalDateTime updatedDate;
	    
	    
	
}
