package com.busreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busreservation.dao.BusStopDao;
import com.busreservation.entity.BusStop;

@Service
public class BusStopServiceImpl implements BusStopService {

	@Autowired
	private BusStopDao busStopDao;
	
	@Override
	public BusStop add(BusStop busStop) {
		// TODO Auto-generated method stub
		return busStopDao.save(busStop);
	}

	@Override
	public BusStop getById(int id) {
		// TODO Auto-generated method stub
		return busStopDao.findById(id).get();
	}

	@Override
	public List<BusStop> getAll() {
		// TODO Auto-generated method stub
		return busStopDao.findAll();
	}

}
