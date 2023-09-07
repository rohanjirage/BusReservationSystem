package com.busreservation.resource;

import java.math.BigDecimal;
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
import com.busreservation.dto.JourneyBookingRequestDto;
import com.busreservation.dto.JourneyBookingResponseDto;
import com.busreservation.entity.Bus;
import com.busreservation.entity.Journey;
import com.busreservation.entity.JourneyBooking;
import com.busreservation.entity.User;
import com.busreservation.service.JourneyBookingService;
import com.busreservation.service.JourneyService;
import com.busreservation.service.UserService;
import com.busreservation.utility.Constants.BusBookingStatus;
import com.busreservation.utility.Constants.BusSeatType;
import com.busreservation.utility.IdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JourneyBookingResource {

	private final Logger LOG = LoggerFactory.getLogger(JourneyBookingResource.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private JourneyBookingService JourneyBookingService;

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private UserService userService;

	public ResponseEntity<CommonApiResponse> addJourneyBooking(JourneyBookingRequestDto request) {

		LOG.info("Received request for add journey");

		CommonApiResponse response = new CommonApiResponse();

		if (request.getBusSeatType() == null || request.getTotalPassengers() == 0
				|| request.getJourneyId() == 0 | request.getPassengerId() == 0) {

			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Journey journey = this.journeyService.getById(request.getJourneyId());

		Bus bus = journey.getBus();

		User passenger = this.userService.getUserById(request.getPassengerId());

		String bookingTime = String
				.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		JourneyBooking booking = new JourneyBooking();

		String bookingId = IdGenerator.generateBookingId();
		booking.setBusSeatType(request.getBusSeatType());

		if (request.getBusSeatType().equals(BusSeatType.BACK.value())) {

			if (journey.getBackSeatsAvailable() < request.getTotalPassengers()) {
				response.setResponseMessage("Seat Unavailable, Failed to reserve the seat");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			BigDecimal totalFare = journey.getBackSeatFare()
					.multiply(BigDecimal.valueOf(request.getTotalPassengers()));

			if (passenger.getWalletAmount().compareTo(totalFare) < 0) {
				response.setResponseMessage("Insufficient Funds in Passenger Wallet");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			booking.setBookingId(bookingId);
			booking.setBookingTime(bookingTime);
			booking.setJourney(journey);
			booking.setPassenger(passenger);
			booking.setStatus(BusBookingStatus.CONFIRMED.value());
			booking.setTotalPassengers(request.getTotalPassengers());

			JourneyBooking passengerBooking = this.JourneyBookingService.add(booking);

			journey.setBackSeatsAvailable(journey.getBackSeatsAvailable() - request.getTotalPassengers());
			journeyService.add(journey);
			
			passenger.setWalletAmount(passenger.getWalletAmount().subtract(totalFare));
			this.userService.updateUser(passenger);

			response.setResponseMessage("Booking Success, your booking id is " + bookingId);
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		else if (request.getBusSeatType().equals(BusSeatType.MIDDLE.value())) {

			if (journey.getMiddleSeatsAvailable() < request.getTotalPassengers()) {
				response.setResponseMessage("Seat Unavailable, Failed to reserve the seat");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			BigDecimal totalFare = journey.getMiddleSeatFare()
					.multiply(BigDecimal.valueOf(request.getTotalPassengers()));

			if (passenger.getWalletAmount().compareTo(totalFare) < 0) {
				response.setResponseMessage("Insufficient Funds in Passenger Wallet");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			booking.setBookingId(bookingId);
			booking.setBookingTime(bookingTime);
			booking.setJourney(journey);
			booking.setPassenger(passenger);
			booking.setStatus(BusBookingStatus.CONFIRMED.value());
			booking.setTotalPassengers(request.getTotalPassengers());

			JourneyBooking passengerBooking = this.JourneyBookingService.add(booking);

			journey.setMiddleSeatsAvailable(journey.getMiddleSeatsAvailable() - request.getTotalPassengers());
			journeyService.add(journey);
			
			passenger.setWalletAmount(passenger.getWalletAmount().subtract(totalFare));
			this.userService.updateUser(passenger);

			response.setResponseMessage("Booking Success, your booking id is " + bookingId);
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		else if (request.getBusSeatType().equals(BusSeatType.FRONT.value())) {

			if (journey.getFrontSeatsAvailable() < request.getTotalPassengers()) {
				response.setResponseMessage("Seat Unavailable, Failed to reserve the seat");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			BigDecimal totalFare = journey.getFrontSeatFare()
					.multiply(BigDecimal.valueOf(request.getTotalPassengers()));

			if (passenger.getWalletAmount().compareTo(totalFare) < 0) {
				response.setResponseMessage("Insufficient Funds in Passenger Wallet");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			booking.setBookingId(bookingId);
			booking.setBookingTime(bookingTime);
			booking.setJourney(journey);
			booking.setPassenger(passenger);
			booking.setStatus(BusBookingStatus.CONFIRMED.value());
			booking.setTotalPassengers(request.getTotalPassengers());

			JourneyBooking passengerBooking = this.JourneyBookingService.add(booking);

			journey.setFrontSeatsAvailable(journey.getFrontSeatsAvailable() - request.getTotalPassengers());
			journeyService.add(journey);
			
			passenger.setWalletAmount(passenger.getWalletAmount().subtract(totalFare));
			this.userService.updateUser(passenger);

			response.setResponseMessage("Booking Success, your booking id is " + bookingId);
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		response.setResponseMessage("Failed to book the seats");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<JourneyBookingResponseDto> fetchAllJourneyBookings() {

		LOG.info("Received request for fetching all bookings");

		JourneyBookingResponseDto response = new JourneyBookingResponseDto();

		List<JourneyBooking> allBookings = new ArrayList<>();

		allBookings = this.JourneyBookingService.getAll();

		if (CollectionUtils.isEmpty(allBookings)) {
			response.setResponseMessage("Failed to book the seats");
			response.setSuccess(true);

			return new ResponseEntity<JourneyBookingResponseDto>(response, HttpStatus.OK);
		}

		response.setBookings(allBookings);
		response.setResponseMessage("Failed to book the seats");
		response.setSuccess(true);

		// Convert the object to a JSON string
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<JourneyBookingResponseDto>(response, HttpStatus.OK);
	}

	public ResponseEntity<JourneyBookingResponseDto> fetchUserBookings(int userId) {

		LOG.info("Received request for fetching user bookings");

		JourneyBookingResponseDto response = new JourneyBookingResponseDto();
		
		if(userId == 0) {
			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<JourneyBookingResponseDto>(response, HttpStatus.BAD_REQUEST);
		}
		
		User passenger = this.userService.getUserById(userId);

		List<JourneyBooking> allBookings = new ArrayList<>();

		allBookings = this.JourneyBookingService.getByPassenger(passenger);

		if (CollectionUtils.isEmpty(allBookings)) {
			response.setResponseMessage("Failed to book the seats");
			response.setSuccess(true);

			return new ResponseEntity<JourneyBookingResponseDto>(response, HttpStatus.OK);
		}

		response.setBookings(allBookings);
		response.setResponseMessage("Failed to book the seats");
		response.setSuccess(true);

		return new ResponseEntity<JourneyBookingResponseDto>(response, HttpStatus.OK);
	}

}
