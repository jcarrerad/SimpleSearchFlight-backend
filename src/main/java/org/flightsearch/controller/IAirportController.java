package org.flightsearch.controller;

import org.flightsearch.controller.wrapper.AirportListWrapper;
import org.flightsearch.controller.wrapper.AirportWrapper;
import org.springframework.http.ResponseEntity;

public interface IAirportController {

	public AirportListWrapper getAirports();
	public ResponseEntity<AirportWrapper> getAirport(String code);
}
