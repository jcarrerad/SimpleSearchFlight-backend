package org.flightsearch.remote;

import java.util.Date;
import java.util.List;

import org.flightsearch.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoteFlightSearch implements IRemoteFlightSearch {

	@Autowired
	DummyFlightFactory dummyFlightFactory;
	
	public List<Flight> search(String origin, String destination,
			Date flightDate) {
		return dummyFlightFactory.createFlightList(origin, destination, flightDate);
	}

}
