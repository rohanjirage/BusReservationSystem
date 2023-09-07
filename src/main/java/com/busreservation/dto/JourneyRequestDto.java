package com.busreservation.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class JourneyRequestDto {
	
	private String departureTime;
	
	private String arrivalTime;
	
	private String status; // Scheduled, On Time, Delayed, etc.

	private int departureBusStopId;

	private int arrivalBusStopId;

	private int busId;
	
    private BigDecimal backSeatFare;
	
	private BigDecimal middleSeatFare;
	
	private BigDecimal frontSeatFare;

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDepartureBusStopId() {
		return departureBusStopId;
	}

	public void setDepartureBusStopId(int departureBusStopId) {
		this.departureBusStopId = departureBusStopId;
	}

	public int getArrivalBusStopId() {
		return arrivalBusStopId;
	}

	public void setArrivalBusStopId(int arrivalBusStopId) {
		this.arrivalBusStopId = arrivalBusStopId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public BigDecimal getBackSeatFare() {
		return backSeatFare;
	}

	public void setBackSeatFare(BigDecimal backSeatFare) {
		this.backSeatFare = backSeatFare;
	}

	public BigDecimal getMiddleSeatFare() {
		return middleSeatFare;
	}

	public void setMiddleSeatFare(BigDecimal middleSeatFare) {
		this.middleSeatFare = middleSeatFare;
	}

	public BigDecimal getFrontSeatFare() {
		return frontSeatFare;
	}

	public void setFrontSeatFare(BigDecimal frontSeatFare) {
		this.frontSeatFare = frontSeatFare;
	}

	
}
