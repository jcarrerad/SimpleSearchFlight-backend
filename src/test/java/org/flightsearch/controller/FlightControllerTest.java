package org.flightsearch.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.flightsearch.domain.Flight;
import org.flightsearch.remote.DummyFlightFactory;
import org.flightsearch.service.IFlightSearch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class FlightControllerTest {
	private MockMvc mockMvc;

    @Mock
    private IFlightSearch iFlightSearch;

    @InjectMocks
    private FlightController flightController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(flightController)
                .build();
    }
    
    @Test
    public void testSearchSuccess() throws Exception {
    	DummyFlightFactory flightFactory = new DummyFlightFactory();
    	String origin = "MEX";
    	String destination = "JFK";
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	String paramDate = dateFormatter.format(new Date());
    	Date flightDate = dateFormatter.parse(paramDate);
    	List<Flight> flights = flightFactory.createFlightList(origin, destination,flightDate);
        when(iFlightSearch.search(origin, destination, flightDate)).thenReturn(flights);
        mockMvc.perform(get("/flights")
        		.param("origin", origin)
        		.param("destination", destination)
        		.param("date", paramDate)
        		)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        
        verify(iFlightSearch, times(1)).search(origin, destination, flightDate);
        verifyNoMoreInteractions(iFlightSearch);
    }
}
