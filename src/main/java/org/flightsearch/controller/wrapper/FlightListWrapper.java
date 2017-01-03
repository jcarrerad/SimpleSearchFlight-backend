package org.flightsearch.controller.wrapper;

import java.util.List;

import org.flightsearch.domain.Flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class FlightListWrapper {

	@JsonProperty("flights")
	private List<Flight> flights;
	

	public FlightListWrapper(List<Flight> flights) {
		super();
		this.flights = flights;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	

}
