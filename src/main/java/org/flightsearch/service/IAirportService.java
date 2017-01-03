package org.flightsearch.service;

import java.util.List;

import org.flightsearch.domain.Airport;

public interface IAirportService {

	public List<Airport> getAll();
	public Airport find(String code);
	public Airport add(Airport customer);
	public String remove(String id);
	public Airport update(String id, Airport customer);
}
