package org.flightsearch.service;

import java.util.Date;
import java.util.List;

import org.flightsearch.domain.Flight;

public interface IFlightSearch {

	public List<Flight> search(String origin, String destination, Date flightDate);
}
