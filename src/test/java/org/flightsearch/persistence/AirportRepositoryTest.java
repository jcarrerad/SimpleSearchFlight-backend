package org.flightsearch.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.flightsearch.config.AppConfig;
import org.flightsearch.config.HibernateTestConfig;
import org.flightsearch.domain.Airport;
import org.flightsearch.persistence.repository.IAirportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
			AppConfig.class, 
			HibernateTestConfig.class})
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
						  DbUnitTestExecutionListener.class, 
						  TransactionalTestExecutionListener.class })
@Transactional
public class AirportRepositoryTest {

	@Autowired
	private IAirportRepository iAirportRepository; 
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testListAll() throws Exception {
		List<Airport> results = iAirportRepository.list();
		assertEquals(3, results.size());
		assertTrue(results.get(0) instanceof Airport);
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testGetByCode() throws Exception {
		Airport result = iAirportRepository.get("MEX");
		assertTrue(result != null);
		assertTrue(result instanceof Airport);
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testGetUnexistentCode() throws Exception {
		Airport result = iAirportRepository.get("YQR");
		assertTrue(result == null);
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testCreate() throws Exception {
		Airport airport = new Airport("ABC", "My new airport", "XYZ");
		iAirportRepository.create(airport);
		assertTrue(airport.getId() != null);
		
		assertTrue(iAirportRepository.list().size() == 4);
		assertTrue(iAirportRepository.get("ABC") != null);
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testDelete() throws Exception {
		String result = iAirportRepository.delete("MEX");
		assertTrue(result != null);
		
		assertTrue(iAirportRepository.list().size() == 2);
		assertTrue(iAirportRepository.get("MEX") == null);
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testDeleteUnexistent() throws Exception {
		String result = iAirportRepository.delete("YQR");
		assertTrue(result == null);
		
		assertTrue(iAirportRepository.list().size() == 3);
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void testUpdate() throws Exception {
		Airport airport = new Airport("MEX", "Updated name", "MEX");
		iAirportRepository.update("MEX", airport);
		
		Airport result = iAirportRepository.get("MEX");
		
		assertTrue(result != null);
		assertEquals(airport.getName(), result.getName()); 
		
	}

}
