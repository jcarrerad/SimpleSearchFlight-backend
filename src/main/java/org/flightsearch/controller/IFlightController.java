package org.flightsearch.controller;

import java.util.Date;

import org.flightsearch.controller.wrapper.FlightListWrapper;
import org.springframework.http.ResponseEntity;

public interface IFlightController {
	
	public ResponseEntity<FlightListWrapper> get(String origin, String destination, Date flightDate);
}
