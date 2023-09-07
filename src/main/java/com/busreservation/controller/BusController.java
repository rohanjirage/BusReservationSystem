package com.busreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busreservation.dto.BusResponseDto;
import com.busreservation.dto.CommonApiResponse;
import com.busreservation.entity.Bus;
import com.busreservation.resource.BusResource;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/bus/")
@CrossOrigin(origins = "http://localhost:3000")
public class BusController {
	
	@Autowired
	private BusResource busResource;
	
	@PostMapping("add")
	@ApiOperation(value = "Api to add the BUS")
	public ResponseEntity<CommonApiResponse> addAirplane(@RequestBody Bus bus) {
		return this.busResource.addBus(bus);
	}
	
	@GetMapping("/fetch/all")
	@ApiOperation(value = "Api to fetch all bus")
	public ResponseEntity<BusResponseDto> fetchAllBus() {
		return this.busResource.fetchAllBus();
	}

}
