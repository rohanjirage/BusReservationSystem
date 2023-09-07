package com.busreservation.dto;

import java.util.ArrayList;
import java.util.List;

import com.busreservation.entity.BusStop;

import lombok.Data;

@Data
public class BusStopResponseDto extends CommonApiResponse {

	private List<BusStop> busStops = new ArrayList<>();

	public List<BusStop> getBusStops() {
		return busStops;
	}

	public void setBusStops(List<BusStop> busStops) {
		this.busStops = busStops;
	}
	
	
	
}
