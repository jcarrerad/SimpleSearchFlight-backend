package org.flightsearch.controller;

import java.util.Date;
import java.util.List;

import org.flightsearch.controller.wrapper.FlightListWrapper;
import org.flightsearch.domain.Flight;
import org.flightsearch.service.IFlightSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController implements IFlightController{

	@Autowired
	private IFlightSearch iFlightSearch;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@GetMapping("/flights")
	public ResponseEntity<FlightListWrapper> get(@RequestParam("origin") @Validated String fromAirportCode, @RequestParam("destination") @Validated String toAirportCode, @RequestParam("date") @Validated @DateTimeFormat(pattern="yyyy-MM-dd") Date flightDate) {
		ResponseEntity<FlightListWrapper> response = null;
		try {
			List<Flight> flights =  iFlightSearch.search(fromAirportCode, toAirportCode, flightDate);
			if(flights == null || flights.isEmpty()){
				response = new ResponseEntity("No Fligths found ",HttpStatus.NOT_FOUND);
			}else{
				response = new ResponseEntity<FlightListWrapper>(new FlightListWrapper(flights), HttpStatus.OK);
			}
			
			
		} catch (IllegalArgumentException e) {
			response =  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}

}
