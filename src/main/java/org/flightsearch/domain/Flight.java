package org.flightsearch.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "flight")
public class Flight {
	private String id;
	private String flightCode;
	private String origin;
	private String destination;
	private String airline;
	private String airlineLogo;
	private String seatClass;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date departure;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	private Date arrival;
	private int stops;
	private BigDecimal price;
	
	
	public String getId() {
		return id;
	}
	
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
		this.id = flightCode;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	

	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public String getDepartureTime() {
		return getTimeFromDate(this.departure);
	}

	public String getArrivalTime() {
		return getTimeFromDate(this.arrival);
	}

	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public String getAirlineLogo() {
		return airlineLogo;
	}

	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}

	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getTravelTime() {
		String time = null;
		if(this.departure != null && this.arrival != null){
			long diff = this.arrival.getTime() - this.departure.getTime();//as given
			
			long hours = diff / (60 * 60 * 1000) % 24;
			long minutes = diff / (60 * 1000) % 60;
			time = String.format("%02d:%02d", hours, minutes);
		}
		return time;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private String getTimeFromDate(Date date){
		String time =null;
		if( date != null){
			DateFormat df = new SimpleDateFormat("HH:mm");
			time = df.format(date);
		}
		return time;
	}

}
