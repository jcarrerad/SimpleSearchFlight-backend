package org.flightsearch.controller;

import org.flightsearch.controller.wrapper.AirportListWrapper;
import org.flightsearch.controller.wrapper.AirportWrapper;
import org.flightsearch.domain.Airport;
import org.flightsearch.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportController implements IAirportController {

	
	@Autowired
	private IAirportService iAirportService;

	
	@GetMapping("/airports")
	public AirportListWrapper getAirports() {
		return new AirportListWrapper(iAirportService.getAll());
	}

	@GetMapping("/airports/{code}")
	public ResponseEntity<AirportWrapper> getAirport(@PathVariable("code") String code) {

		Airport airport = iAirportService.find(code);
		if (airport == null) {
			return new ResponseEntity("No Airport found for ID " + code, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AirportWrapper>(new AirportWrapper(airport), HttpStatus.OK);
	}
}
