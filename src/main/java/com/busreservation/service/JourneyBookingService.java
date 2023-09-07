package com.busreservation.service;

import java.util.List;

import com.busreservation.entity.Journey;
import com.busreservation.entity.JourneyBooking;
import com.busreservation.entity.User;

public interface JourneyBookingService {
	
	JourneyBooking add(JourneyBooking journeyBooking);
	JourneyBooking getById(int id);
	List<JourneyBooking> getAll();
	List<JourneyBooking> getByPassenger(User user);
	List<JourneyBooking> getByJourney(Journey journey);

}
