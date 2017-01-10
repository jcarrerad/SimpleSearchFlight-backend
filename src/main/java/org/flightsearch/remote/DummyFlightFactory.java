package org.flightsearch.remote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.flightsearch.domain.Flight;
import org.flightsearch.remote.model.RemoteFlight;
import org.flightsearch.remote.model.RemoteFlightList;
import org.springframework.stereotype.Component;

@Component
public class DummyFlightFactory {
	
	private static final String SEAT_CLASS ="Economy / Coach";
	
	
	
	public static final List<String> AIRLINES = Arrays.asList(
		    "American Airlines",
		    "Delta Airlines",
		    "Continental Airlines",
		    "Aeromexico"
	    );
	
	public static final List<String> FLIGHT_CODES = Arrays.asList(
		    "A501",
		    "D604",
		    "C301",
		    "A504"
	    );
	
	public static final List<Integer> STOPS = Arrays.asList(
		    0,1,2,1
	    );
	
	public static final List<String> PRICES = Arrays.asList(
		    "521",
		    "480",
		    "420",
		    "540"
	    );
	
	
	public static final List<String> DEPARTURE_TIME = Arrays.asList(
		    "05:20",
		    "12:45",
		    "19:35",
		    "22:00"
	    );
	
	public static final List<String> FLIGHT_TIME = Arrays.asList(
		    "03:40",
		    "04:10",
		    "04:50",
		    "03:50"
	    );
	
	public static final List<String> AIRLINE_LOGO = Arrays.asList(
		    "http://netdna.webdesignerdepot.com/uploads/2009/03/aa2.gif",
		    "http://logok.org/wp-content/uploads/2014/02/Delta-Arrow-logo.png",
		    "http://fontmeme.com/images/Continental-Airlines-Logo.jpg",
		    "https://aeromexico.com/export/sites/default/.galleries/informativos-espanol/cabllero_aguila_noticia.jpg"
	    );

	public List<Flight> createFlightList(String origin, String destination, Date departure){
		List<Flight> flights = new ArrayList<Flight>();
		
		for(int i = 0; i < AIRLINES.size(); i++){
			Date departureTemp = new Date(departure.getTime());
			Flight flight = createFlight(i);
			flight.setOrigin(origin);
			flight.setDestination(destination);
			departureTemp = addDepartureTime(departureTemp, i);
			flight.setDeparture(departureTemp);
			flight.setArrival(calculateArrival(departureTemp, i));
			
			flights.add(flight);
		}
		
		return flights;
	}
	
	public RemoteFlightList createRemoteFlightList(String origin, String destination, Date departure){
		RemoteFlightList flightList = new RemoteFlightList();
		List<RemoteFlight> flights = new ArrayList<RemoteFlight>();
		
		for(int i = 0; i < AIRLINES.size(); i++){
			Date departureTemp = new Date(departure.getTime());
			RemoteFlight flight = createRemoteFlight(i);
			flight.setOrigin(origin);
			flight.setDestination(destination);
			departureTemp = addDepartureTime(departureTemp, i);
			flight.setDeparture(getGregorianCalendarOfDate(departureTemp));
			flight.setArrival(getGregorianCalendarOfDate((calculateArrival(departureTemp, i))));
			
			flights.add(flight);
		}
		flightList.setFlights(flights);
		
		return flightList;
	}
	
	private Date addDepartureTime(Date departure, int index){
		String time  = DEPARTURE_TIME.get(index);
		StringTokenizer tokenizer = new StringTokenizer(time, ":");
		String hourStr = tokenizer.nextToken();
		String minuteStr = tokenizer.nextToken();
		int hour = Integer.valueOf(hourStr);
		int minute = Integer.valueOf(minuteStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(departure);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	private Date calculateArrival(Date departure, int index){
		String time  = FLIGHT_TIME.get(index);
		StringTokenizer tokenizer = new StringTokenizer(time, ":");
		int hour = Integer.valueOf(tokenizer.nextToken());
		int minute = Integer.valueOf(tokenizer.nextToken());
		Calendar cal = Calendar.getInstance();
		cal.setTime(departure);
		cal.add(Calendar.HOUR, hour);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	
	private Flight createFlight(int index){
		Flight flight = new Flight();
		flight.setFlightCode(FLIGHT_CODES.get(index));
		flight.setAirline(AIRLINES.get(index));
		flight.setStops(STOPS.get(index));
		flight.setPrice(new BigDecimal(PRICES.get(index)));
		flight.setSeatClass(SEAT_CLASS);
		flight.setAirlineLogo(AIRLINE_LOGO.get(index));
		
		return flight;
	}
	
	private RemoteFlight createRemoteFlight(int index){
		RemoteFlight flight = new RemoteFlight();
		flight.setFlightCode(FLIGHT_CODES.get(index));
		flight.setAirline(AIRLINES.get(index));
		flight.setStops(STOPS.get(index));
		flight.setPrice(PRICES.get(index));
		flight.setSeatClass(SEAT_CLASS);
		flight.setAirlineLogo(AIRLINE_LOGO.get(index));
		
		return flight;
	}
	
	// conversi?n de Date a XMLGregorianCalendar
    private XMLGregorianCalendar getGregorianCalendarOfDate(Date date) {
        GregorianCalendar gregorianCalendario = new GregorianCalendar();
        XMLGregorianCalendar xmlGregorianCalendar = null;
        gregorianCalendario.setTime(date);
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendario);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }
	
}
