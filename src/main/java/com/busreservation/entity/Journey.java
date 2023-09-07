package com.busreservation.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Journey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String journeyNumber;
	
	private String busDepartureTime;
	
	private String busArrivalTime;
	
	private String status; // Scheduled, On Time, Delayed, etc.

	@ManyToOne
	@JoinColumn(name = "departure_busstop_id")
	private BusStop departureBusStop;

	@ManyToOne
	@JoinColumn(name = "arrival_busstop_id")
	private BusStop arrivalBusStop;

	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
	private BigDecimal frontSeatFare;
	
	private BigDecimal middleSeatFare;
	
	private BigDecimal backSeatFare;
	
	
	// from Airplane Entity
    private int totalSeat;
    
    private int frontSeats;
	
    private int middleSeats;
    
    private int backSeats;
	
    private int frontSeatsAvailable;
	
    private int middleSeatsAvailable;
    
    private int backSeatsAvailable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJourneyNumber() {
		return journeyNumber;
	}

	public void setJourneyNumber(String journeyNumber) {
		this.journeyNumber = journeyNumber;
	}

	public String getBusDepartureTime() {
		return busDepartureTime;
	}

	public void setBusDepartureTime(String busDepartureTime) {
		this.busDepartureTime = busDepartureTime;
	}

	public String getBusArrivalTime() {
		return busArrivalTime;
	}

	public void setBusArrivalTime(String busArrivalTime) {
		this.busArrivalTime = busArrivalTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BusStop getDepartureBusStop() {
		return departureBusStop;
	}

	public void setDepartureBusStop(BusStop departureBusStop) {
		this.departureBusStop = departureBusStop;
	}

	public BusStop getArrivalBusStop() {
		return arrivalBusStop;
	}

	public void setArrivalBusStop(BusStop arrivalBusStop) {
		this.arrivalBusStop = arrivalBusStop;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public BigDecimal getFrontSeatFare() {
		return frontSeatFare;
	}

	public void setFrontSeatFare(BigDecimal frontSeatFare) {
		this.frontSeatFare = frontSeatFare;
	}

	public BigDecimal getMiddleSeatFare() {
		return middleSeatFare;
	}

	public void setMiddleSeatFare(BigDecimal middleSeatFare) {
		this.middleSeatFare = middleSeatFare;
	}

	public BigDecimal getBackSeatFare() {
		return backSeatFare;
	}

	public void setBackSeatFare(BigDecimal backSeatFare) {
		this.backSeatFare = backSeatFare;
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

	public int getFrontSeatsAvailable() {
		return frontSeatsAvailable;
	}

	public void setFrontSeatsAvailable(int frontSeatsAvailable) {
		this.frontSeatsAvailable = frontSeatsAvailable;
	}

	public int getMiddleSeatsAvailable() {
		return middleSeatsAvailable;
	}

	public void setMiddleSeatsAvailable(int middleSeatsAvailable) {
		this.middleSeatsAvailable = middleSeatsAvailable;
	}

	public int getBackSeatsAvailable() {
		return backSeatsAvailable;
	}

	public void setBackSeatsAvailable(int backSeatsAvailable) {
		this.backSeatsAvailable = backSeatsAvailable;
	}
    
    

}
