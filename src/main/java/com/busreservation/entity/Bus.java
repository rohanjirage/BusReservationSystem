package com.busreservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private String registrationNumber;
	
	private int totalSeat;
	
    private int frontSeats;
	
    private int middleSeats;
    
    private int backSeats; 
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public int getFrontSeats() {
		return frontSeats;
	}

	public void setFrontSeats(int frontSeats) {
		this.frontSeats = frontSeats;
	}

	public int getMiddleSeats() {
		return middleSeats;
	}

	public void setMiddleSeats(int middleSeats) {
		this.middleSeats = middleSeats;
	}

	public int getBackSeats() {
		return backSeats;
	}

	public void setBackSeats(int backSeats) {
		this.backSeats = backSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
