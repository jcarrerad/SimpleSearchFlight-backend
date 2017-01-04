package org.flightsearch.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.flightsearch.domain.Airport;
import org.flightsearch.persistence.model.AirportEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AirportRepository extends AbstractRepository implements IAirportRepository {
	
	public Airport get(String code) {
		AirportEntity airportEntity = (AirportEntity)getSession().get(AirportEntity.class, new String(code));
		return airportEntity != null ? createAirport(airportEntity) : null;
	}

	public Airport create(Airport airport) {
		AirportEntity airportEntity = new AirportEntity(airport);
		getSession().persist(airportEntity);
		airport.setId(airportEntity.getCode());
		return airport;
	}

	public String delete(String code) {
		AirportEntity airport = (AirportEntity)getSession().get(AirportEntity.class, new String(code));
		if(airport != null){
			getSession().delete(airport);
			return code;
		}
		return null;
	}

	public Airport update(String code, Airport airport) {
		getSession().saveOrUpdate(new AirportEntity(airport));
		return airport;
	}

	@SuppressWarnings("unchecked")
	public List<Airport> list() {
		List<AirportEntity> entities = null;
		List<Airport> airports = new ArrayList<Airport>();
		log.debug("Fetching from database..");
		entities = getSession().createCriteria(AirportEntity.class).list();
		for(AirportEntity entity : entities){
			airports.add(createAirport(entity));
		}
		return airports;
	}
	
	
	private Airport createAirport(AirportEntity entity){
		return new Airport(entity.getCode(),
							entity.getName(),
							entity.getCountry());
	}

}
