package org.flightsearch.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.flightsearch.domain.Flight;
import org.flightsearch.remote.proxy.IRemoteFlightSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightSearch implements IFlightSearch {

	@Autowired
	IRemoteFlightSearch iRemoteFlightSearch;
	
	private static final String INVALID_FORMAT_CODE = "Invalid format for airport code ";
	private static final String INVALID_DATE = "Invalid date ";
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<Flight> search(String origin, String destination, Date flightDate) {
		if(!isCodeValid(origin)){
			throw new IllegalArgumentException(INVALID_FORMAT_CODE + origin);
		}
		if(!isCodeValid(destination)){
			throw new IllegalArgumentException(INVALID_FORMAT_CODE + destination);
		}
		
		if(!isDateValid(flightDate)){
			
			throw new IllegalArgumentException(INVALID_DATE + DATE_FORMATTER.format(flightDate));
		}
		
		return iRemoteFlightSearch.search(origin.toUpperCase(), destination.toUpperCase(), flightDate);
	}
	
	private boolean isCodeValid(String code){
		boolean isValid = false;
		if(code != null && !code.trim().isEmpty()){
			Pattern pattern = Pattern.compile("[A-Za-z]{3}");
			isValid = pattern.matcher(code).matches();
		}
		return isValid;
	}
	
	private boolean isDateValid(Date date){
		Calendar c = Calendar.getInstance();

		// set the calendar to start of today
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		Date today = c.getTime();
		
		return (!date.before(today));
	}

}
