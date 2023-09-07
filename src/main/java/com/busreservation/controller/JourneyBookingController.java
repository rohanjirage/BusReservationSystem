package com.busreservation.controller;

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
import com.busreservation.dto.JourneyBookingRequestDto;
import com.busreservation.dto.JourneyBookingResponseDto;
import com.busreservation.resource.JourneyBookingResource;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/journey/book")
@CrossOrigin(origins = "http://localhost:3000")
public class JourneyBookingController {
	
	@Autowired
	private JourneyBookingResource journeyBookingResource;
	
	@PostMapping("add")
	@ApiOperation(value = "Api to add the Journey Booking")
	public ResponseEntity<CommonApiResponse> addAirport(@RequestBody JourneyBookingRequestDto request) {
		return this.journeyBookingResource.addJourneyBooking(request);
	}
	
	@GetMapping("/fetch/all")
	@ApiOperation(value = "Api to fetch all journey bookings")
	public ResponseEntity<JourneyBookingResponseDto> fetchAllJourneys() {
		return this.journeyBookingResource.fetchAllJourneyBookings();
	}
	
	@GetMapping("/fetch/user")
	@ApiOperation(value = "Api to fetch user journey bookings")
	public ResponseEntity<JourneyBookingResponseDto> fetchUserBookings(@RequestParam("userId") int userId) {
		return this.journeyBookingResource.fetchUserBookings(userId);
	}

}