package org.flightsearch.persistence.repository;

import java.util.List;

import org.flightsearch.domain.Airport;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


public interface IAirportRepository {
	@Cacheable("airports")
	public List<Airport> list();
	@Cacheable("airports")
	public Airport get(String code);
	@CacheEvict(cacheNames="airports", allEntries=true)
	public Airport create(Airport customer);
	@CacheEvict(cacheNames="airports", allEntries=true)
	public String delete(String code);
	@CachePut(cacheNames="airports", key="#code")
	public Airport update(String code, Airport customer);
	

}
