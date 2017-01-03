package org.flightsearch.persistence.repository;

import java.util.List;

import org.flightsearch.domain.Airport;

public interface IAirportRepository {
	public List<Airport> list();
	public Airport get(String code);
	public Airport create(Airport customer);
	public String delete(String code);
	public Airport update(String code, Airport customer);
	

}
