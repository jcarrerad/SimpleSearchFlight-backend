package org.flightsearch.remote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.flightsearch.domain.Flight;
import org.springframework.stereotype.Component;

@Component
public class DummyFlightFactory {
	
	private static final String SEAT_CLASS ="Economy / Coach";
	
	
	
	public static final List<String> AIRLINES = asList(
		    "American Airlines",
		    "Delta Airlines",
		    "Continental Airlines",
		    "Aeromexico"
	    );
	
	public static final List<String> FLIGHT_CODES = asList(
		    "A501",
		    "D604",
		    "C301",
		    "A504"
	    );
	
	public static final List<Integer> STOPS = asList(
		    0,1,2,1
	    );
	
	public static final List<String> PRICES = asList(
		    "521",
		    "480",
		    "420",
		    "540"
	    );
	
	
	public static final List<String> DEPARTURE_TIME = asList(
		    "05:20",
		    "12:45",
		    "19:35",
		    "22:00"
	    );
	
	public static final List<String> FLIGHT_TIME = asList(
		    "03:40",
		    "04:10",
		    "04:50",
		    "03:50"
	    );
	
	public static final List<String> AIRLINE_LOGO = asList(
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
	

	private static <T> List<T> asList(T ... items){
		List<T> list = new ArrayList<T>();
		if(items != null && items.length > 0)
			for(T item : items)
				list.add(item);
		return list;
	}

}
