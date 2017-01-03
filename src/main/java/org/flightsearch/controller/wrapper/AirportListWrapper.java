package org.flightsearch.controller.wrapper;

import java.util.List;

import org.flightsearch.domain.Airport;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class AirportListWrapper{

	@JsonProperty("airports")
	private List<Airport> airports;

	public AirportListWrapper(List<Airport> airports) {
		super();
		this.airports = airports;
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}
	
	

}
