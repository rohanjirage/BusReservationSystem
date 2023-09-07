package com.busreservation.service;

import java.util.List;

import com.busreservation.entity.Bus;

public interface BusService {
	
	Bus add(Bus bus);
	Bus getById(int id);
	List<Bus> getAll();
	
}
