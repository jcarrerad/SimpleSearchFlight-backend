package org.flightsearch.controller.wrapper;

import org.flightsearch.domain.Airport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportWrapper {
	@JsonProperty("airport")
	private Airport airport;
	
	public AirportWrapper(Airport airport) {
		super();
		this.airport = airport;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}
	
	
}
