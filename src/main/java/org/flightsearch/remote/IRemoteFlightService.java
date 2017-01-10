package org.flightsearch.remote;

import java.util.Date;

import org.w3c.dom.Document;

public interface IRemoteFlightService {
	
	public Document fetchFlights(String origin, String destination, Date flightDate);

}
