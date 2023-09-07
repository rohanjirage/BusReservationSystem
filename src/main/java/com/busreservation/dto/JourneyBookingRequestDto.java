package com.busreservation.dto;

import lombok.Data;

@Data
public class JourneyBookingRequestDto {
	
    private int totalPassengers;
    
    private String busSeatType;
    
    private int passengerId;

    private int journeyId;

	public int getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

	public String getBusSeatType() {
		return busSeatType;
	}

	public void setBusSeatType(String busSeatType) {
		this.busSeatType = busSeatType;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

    
}
