package com.busreservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busreservation.entity.Journey;
import com.busreservation.entity.JourneyBooking;
import com.busreservation.entity.User;

@Repository
public interface JourneyBookingDao extends JpaRepository<JourneyBooking, Integer> {
	
	List<JourneyBooking> findByPassenger(User user);
	List<JourneyBooking> findByJourney(Journey journey);

}
