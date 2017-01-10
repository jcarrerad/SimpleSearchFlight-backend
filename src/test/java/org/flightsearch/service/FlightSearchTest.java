package org.flightsearch.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.flightsearch.domain.Flight;
import org.flightsearch.remote.proxy.IRemoteFlightSearch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FlightSearchTest {

	@Mock
	IRemoteFlightSearch iRemoteFlightSearch;
	 
	@InjectMocks
	FlightSearch flightSearch;
	

	private static final String VALID_CODE ="ABC";
	
	 @Before
	 public void initMocks(){
		 MockitoAnnotations.initMocks(this);
	  }

	@Test
	public void testSuccessfulSearch(){
			Date flightDate = new Date();
			List<Flight> flights = Arrays.asList(new Flight());
			when(iRemoteFlightSearch.search(any(String.class), any(String.class), any(Date.class))).thenReturn(flights);
			List<Flight> result = flightSearch.search(VALID_CODE, VALID_CODE, flightDate);
			assertThat(result, is(flights));
		}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingWithNumericOriginCode(){
		flightSearch.search("123", VALID_CODE, new Date());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingMaxLengthOriginCode(){
		flightSearch.search("ABCD", VALID_CODE, new Date());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingMinLengthOriginCode(){
		flightSearch.search("AB", VALID_CODE, new Date());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingWithNumericDestinationCode(){
		flightSearch.search(VALID_CODE, "123", new Date());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingMaxLengthDestinationCode(){
		flightSearch.search(VALID_CODE, "ABCD", new Date());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingMinLengthDestinationCode(){
		flightSearch.search(VALID_CODE, "AB", new Date());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailingPastDateCode(){
		Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
		flightSearch.search(VALID_CODE, VALID_CODE, yesterday);
	}
	
	
}
