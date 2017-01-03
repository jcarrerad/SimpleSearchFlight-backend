package org.flightsearch.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.flightsearch.domain.Airport;
import org.flightsearch.service.IAirportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AirportControllerTest {

	private MockMvc mockMvc;

    @Mock
    private IAirportService iAirportService;

    @InjectMocks
    private AirportController airportController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(airportController)
                .build();
    }
    
    @Test
    public void testGetAllSuccess() throws Exception {
    	List<Airport> airportList = Arrays.asList(
                new Airport("MEX","BENITO JUAREZ AIRPORT", "MEX"),
                new Airport("JFK","JOHN F KENNEDY INTL AIRPORT", "USA"));
        when(iAirportService.getAll()).thenReturn(airportList);
        mockMvc.perform(get("/airports"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.airports", hasSize(2)))
                .andExpect(jsonPath("$.airports[0].code", is("MEX")))
                .andExpect(jsonPath("$.airports[0].name", is("BENITO JUAREZ AIRPORT")))
                .andExpect(jsonPath("$.airports[0].country", is("MEX")))
                .andExpect(jsonPath("$.airports[1].code", is("JFK")))
                .andExpect(jsonPath("$.airports[1].name", is("JOHN F KENNEDY INTL AIRPORT")))
        		.andExpect(jsonPath("$.airports[1].country", is("USA")));
        verify(iAirportService, times(1)).getAll();
        verifyNoMoreInteractions(iAirportService);
    }
    
    
    @Test
    public void testGetByIdSuccess() throws Exception {
    	Airport airport = new Airport("JFK","JOHN F KENNEDY INTL AIRPORT", "USA");
    	String airportCode = "JFK";
        when(iAirportService.find(airportCode)).thenReturn(airport);
        
        mockMvc.perform(get("/airports/{code}",airportCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.airport.code", is("JFK")))
                .andExpect(jsonPath("$.airport.name", is("JOHN F KENNEDY INTL AIRPORT")))
        		.andExpect(jsonPath("$.airport.country", is("USA")));
        verify(iAirportService, times(1)).find(airportCode);
        verifyNoMoreInteractions(iAirportService);
    }
    
    @Test
    public void testGetByIdNotFound() throws Exception {
    	String airportCode = "JFK";
        when(iAirportService.find(airportCode)).thenReturn(null);
        mockMvc.perform(get("/airports/{code}",airportCode))
                .andExpect(status().is(404));
        verify(iAirportService, times(1)).find(airportCode);
        verifyNoMoreInteractions(iAirportService);
    }
}
