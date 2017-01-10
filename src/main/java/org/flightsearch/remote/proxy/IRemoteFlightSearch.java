package org.flightsearch.remote.proxy;

import java.util.Date;
import java.util.List;

import org.flightsearch.domain.Flight;

public interface IRemoteFlightSearch {

	public List<Flight> search(String origin, String destination, Date flightDate);
}
