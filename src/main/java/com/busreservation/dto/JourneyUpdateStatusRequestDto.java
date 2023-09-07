package com.busreservation.dto;

import lombok.Data;

@Data
public class JourneyUpdateStatusRequestDto {
	
	private int journeyId;
	
	private String status;

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
