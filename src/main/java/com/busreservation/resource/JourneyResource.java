package com.busreservation.resource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.busreservation.dto.CommonApiResponse;
import com.busreservation.dto.JourneyRequestDto;
import com.busreservation.dto.JourneyResponseDto;
import com.busreservation.dto.JourneyUpdateStatusRequestDto;
import com.busreservation.entity.Bus;
import com.busreservation.entity.BusStop;
import com.busreservation.entity.Journey;
import com.busreservation.service.BusService;
import com.busreservation.service.BusStopService;
import com.busreservation.service.JourneyService;
import com.busreservation.utility.Constants.BusSeatType;
import com.busreservation.utility.Constants.JourneyStatus;
import com.busreservation.utility.IdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JourneyResource {
	
	private final Logger LOG = LoggerFactory.getLogger(JourneyResource.class);

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private JourneyService journeyService;
	
	@Autowired
	private BusStopService busStopService;
	
	@Autowired
	private BusService busService;

	public ResponseEntity<CommonApiResponse> addJourney(JourneyRequestDto journeyRequest) {

		LOG.info("Received request for add journey");

		CommonApiResponse response = new CommonApiResponse();

		if (journeyRequest == null || journeyRequest.getBusId() == 0 
				|| journeyRequest.getArrivalBusStopId() == 0 | journeyRequest.getDepartureBusStopId() == 0) {
			
			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		BusStop arrivalBusStop = this.busStopService.getById(journeyRequest.getArrivalBusStopId());
		BusStop departureBusStop = this.busStopService.getById(journeyRequest.getDepartureBusStopId());
		
		Bus bus = this.busService.getById(journeyRequest.getBusId());
		
		Journey journey = new Journey();
		journey.setBusArrivalTime(journeyRequest.getArrivalTime());
		journey.setBus(bus);
		journey.setArrivalBusStop(arrivalBusStop);
		journey.setDepartureBusStop(departureBusStop);
		journey.setBusDepartureTime(journeyRequest.getDepartureTime());
		journey.setStatus(JourneyStatus.SCHEDULED.value());
		journey.setTotalSeat(bus.getTotalSeat());
		journey.setFrontSeats(bus.getFrontSeats());
		journey.setBackSeats(bus.getBackSeats());
		journey.setMiddleSeats(bus.getMiddleSeats());
		journey.setMiddleSeatsAvailable(bus.getMiddleSeats());
		journey.setBackSeatsAvailable(bus.getBackSeats());
		journey.setFrontSeatsAvailable(bus.getFrontSeats());
		journey.setJourneyNumber(IdGenerator.generateJourneyNumber());
		journey.setBackSeatFare(journeyRequest.getBackSeatFare());
		journey.setMiddleSeatFare(journeyRequest.getMiddleSeatFare());
		journey.setFrontSeatFare(journeyRequest.getFrontSeatFare());

		Journey addedJourney = this.journeyService.add(journey);

		if (addedJourney == null) {
			response.setResponseMessage("Failed to add the Journey");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Convert the object to a JSON string
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setResponseMessage("Journey added succcessfully!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<JourneyResponseDto> fetchAllJourneys() {

		JourneyResponseDto response = new JourneyResponseDto();
		
		String startTime = String
				.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		List<Journey> journeys = new ArrayList<>();
		
		java.util.Arrays.asList(JourneyStatus.CANCELED.value());

		journeys = this.journeyService.getByDepartureTimeGreaterThanEqualAndStatusNotIn(String.valueOf(startTime), java.util.Arrays.asList(JourneyStatus.CANCELED.value(),
				JourneyStatus.COMPLETED.value()));

		if (CollectionUtils.isEmpty(journeys)) {
			response.setResponseMessage("Journeys fetched success");
			response.setSuccess(true);

			return new ResponseEntity<JourneyResponseDto>(response, HttpStatus.OK);
		}

		response.setJourneys(journeys);
		response.setResponseMessage("BusStop fetched success");
		response.setSuccess(true);

		return new ResponseEntity<JourneyResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<JourneyResponseDto> fetchAllJourneysByTimeRange(int fromBusStopId, int toBusStopId ,String startTime, String endTime) {

		JourneyResponseDto response = new JourneyResponseDto();
		
		if(startTime == null || endTime == null || fromBusStopId ==0 || toBusStopId == 0) {
			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<JourneyResponseDto>(response, HttpStatus.BAD_REQUEST);	
		}
		
		BusStop fromBusStop = this.busStopService.getById(fromBusStopId);
		BusStop toBusStop = this.busStopService.getById(toBusStopId);

		List<Journey> journeys = new ArrayList<>();
		
		java.util.Arrays.asList(JourneyStatus.CANCELED.value());

		journeys = this.journeyService.getByDepartureBusStopAndArrivalBusStopAndDepartureTimeBetweenAndStatusNotIn(fromBusStop, toBusStop, 
				String.valueOf(startTime), String.valueOf(endTime) , java.util.Arrays.asList(JourneyStatus.CANCELED.value(),
						JourneyStatus.COMPLETED.value()));

		if (CollectionUtils.isEmpty(journeys)) {
			response.setResponseMessage("Journeys fetched success");
			response.setSuccess(true);

			return new ResponseEntity<JourneyResponseDto>(response, HttpStatus.OK);
		}

		response.setJourneys(journeys);
		response.setResponseMessage("BusStop fetched success");
		response.setSuccess(true);

		return new ResponseEntity<JourneyResponseDto>(response, HttpStatus.OK);

	}

	public ResponseEntity<List<String>> fetchAllJourneyStatus() {
		
		List<String> journeyStatus = new ArrayList<>();
		
		for(JourneyStatus status: JourneyStatus.values()) {
			journeyStatus.add(status.value());
		}
		
		return new ResponseEntity<List<String>>(journeyStatus, HttpStatus.OK);
	}

	
    public ResponseEntity<List<String>> fetchAllJourneyClass() {
		
		List<String> journeyClass = new ArrayList<>();
		
		for(BusSeatType seatType: BusSeatType.values()) {
			journeyClass.add(seatType.value());
		}
		
		return new ResponseEntity<List<String>>(journeyClass, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> updateJourneyStatus(JourneyUpdateStatusRequestDto request) {
		
		LOG.info("Received request for update journey status");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		Journey journey = this.journeyService.getById(request.getJourneyId());
		journey.setStatus(request.getStatus());
		
	    this.journeyService.add(journey);
	    
	    response.setResponseMessage("Journey status updated successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

}
