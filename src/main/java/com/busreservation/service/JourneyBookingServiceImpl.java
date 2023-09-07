package com.busreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busreservation.dao.JourneyBookingDao;
import com.busreservation.entity.Journey;
import com.busreservation.entity.JourneyBooking;
import com.busreservation.entity.User;

@Service
public class JourneyBookingServiceImpl implements JourneyBookingService {
	
	@Autowired
	private JourneyBookingDao journeyBookingDao;

	@Override
	public JourneyBooking add(JourneyBooking journeyBooking) {
		// TODO Auto-generated method stub
		return journeyBookingDao.save(journeyBooking);
	}

	@Override
	public JourneyBooking getById(int id) {
		// TODO Auto-generated method stub
		return journeyBookingDao.findById(id).get();
	}

	@Override
	public List<JourneyBooking> getAll() {
		// TODO Auto-generated method stub
		return journeyBookingDao.findAll();
	}

	@Override
	public List<JourneyBooking> getByPassenger(User user) {
		// TODO Auto-generated method stub
		return journeyBookingDao.findByPassenger(user);
	}

	@Override
	public List<JourneyBooking> getByJourney(Journey journey) {
		// TODO Auto-generated method stub
		return journeyBookingDao.findByJourney(journey);
	}

}
