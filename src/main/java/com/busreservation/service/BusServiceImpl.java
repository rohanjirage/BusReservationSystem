package com.busreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busreservation.dao.BusDao;
import com.busreservation.entity.Bus;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
	private BusDao busDao;

	@Override
	public Bus add(Bus bus) {
		// TODO Auto-generated method stub
		return busDao.save(bus);
	}

	@Override
	public Bus getById(int id) {
		// TODO Auto-generated method stub
		return busDao.findById(id).get();
	}

	@Override
	public List<Bus> getAll() {
		// TODO Auto-generated method stub
		return busDao.findAll();
	}

}
