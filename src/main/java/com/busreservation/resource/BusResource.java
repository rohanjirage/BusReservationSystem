package com.busreservation.resource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.busreservation.dto.BusResponseDto;
import com.busreservation.dto.CommonApiResponse;
import com.busreservation.entity.Bus;
import com.busreservation.service.BusService;
import com.busreservation.utility.Constants.BusStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BusResource {
	
	private final Logger LOG = LoggerFactory.getLogger(BusStopResource.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private BusService busService;

	public ResponseEntity<CommonApiResponse> addBus(Bus bus) {

		LOG.info("Received request for add bus");

		CommonApiResponse response = new CommonApiResponse();

		if (bus == null) {
			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		bus.setStatus(BusStatus.ACTIVE.value());

		Bus addedBus = this.busService.add(bus);

		if (addedBus == null) {
			response.setResponseMessage("Failed to add the bus");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		response.setResponseMessage("Bus added successful");
		response.setSuccess(true);

		// Convert the object to a JSON string
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<BusResponseDto> fetchAllBus() {

		BusResponseDto response = new BusResponseDto();

		List<Bus> buses = new ArrayList<>();

		buses = this.busService.getAll();

		if (CollectionUtils.isEmpty(buses)) {
			response.setResponseMessage("Bus fetched success");
			response.setSuccess(true);

			return new ResponseEntity<BusResponseDto>(response, HttpStatus.OK);
		}

		response.setBuses(buses);
		response.setResponseMessage("Bus fetched success");
		response.setSuccess(true);

		return new ResponseEntity<BusResponseDto>(response, HttpStatus.OK);

	}

}
