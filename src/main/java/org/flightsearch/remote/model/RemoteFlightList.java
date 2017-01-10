package org.flightsearch.remote.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FlightList")
public class RemoteFlightList {
	private List<RemoteFlight> flights;

	@XmlElement(name="flight")
	public List<RemoteFlight> getFlights() {
		return flights;
	}

	public void setFlights(List<RemoteFlight> flights) {
		this.flights = flights;
	}
}
