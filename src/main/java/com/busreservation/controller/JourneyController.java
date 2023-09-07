package com.busreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busreservation.dto.CommonApiResponse;
import com.busreservation.dto.JourneyRequestDto;
import com.busreservation.dto.JourneyResponseDto;
import com.busreservation.dto.JourneyUpdateStatusRequestDto;
import com.busreservation.resource.JourneyResource;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/journey/")
@CrossOrigin(origins = "http://localhost:3000")
public class JourneyController {
	
	@Autowired
	private JourneyResource journeyResource;
	
	@PostMapping("add")
	@ApiOperation(value = "Api to add the Journey")
	public ResponseEntity<CommonApiResponse> addJourney(@RequestBody JourneyRequestDto request) {
		return this.journeyResource.addJourney(request);
	}
	
	@GetMapping("/fetch/all")
	@ApiOperation(value = "Api to fetch all journeys")
	public ResponseEntity<JourneyResponseDto> fetchAllJourneys() {
		return journeyResource.fetchAllJourneys();
	}
	
	@GetMapping("/search")
	@ApiOperation(value = "Api to search the journeys")
	public ResponseEntity<JourneyResponseDto> searchJourneys(@RequestParam("fromBusStopId") int fromBusStopId, @RequestParam("endBusStopId") int endBusStopId
			,@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
		return journeyResource.fetchAllJourneysByTimeRange(fromBusStopId, endBusStopId ,startTime, endTime);
	}
	
	@GetMapping("/status/all")
	@ApiOperation(value = "Api to fetch all status")
	public ResponseEntity<List<String>> fetchJourneys() {
		return journeyResource.fetchAllJourneyStatus();
	}
	
	@GetMapping("/class/all")
	@ApiOperation(value = "Api to fetch all status")
	public ResponseEntity<List<String>> fetchJourneyClass() {
		return journeyResource.fetchAllJourneyClass();
	}
	
	@PostMapping("update/status")
	@ApiOperation(value = "Api to update the Journey status")
	public ResponseEntity<CommonApiResponse> updateJourneyStatus(@RequestBody JourneyUpdateStatusRequestDto request) {
		return this.journeyResource.updateJourneyStatus(request);
	}
}
