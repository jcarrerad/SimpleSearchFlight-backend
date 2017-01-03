package org.flightsearch.service;

import java.util.List;

import org.flightsearch.domain.Airport;
import org.flightsearch.persistence.repository.IAirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AirportService implements IAirportService {

	@Autowired
	private IAirportRepository iAirportRepository;
	
	@Override
	public List<Airport> getAll() {
		return iAirportRepository.list();
	}

	@Override
	public Airport find(String code) {
		return iAirportRepository.get(code);
	}

	@Override
	public Airport add(Airport customer) {
		return iAirportRepository.create(customer);
	}

	@Override
	public String remove(String code) {
		return iAirportRepository.delete(code);
	}

	@Override
	public Airport update(String code, Airport customer) {
		return iAirportRepository.update(code, customer);
	}

}
