package org.flightsearch.remote.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;


public class RemoteFlight {


	private String id;
	private String flightCode;
	private String origin;
	private String destination;
	private String airline;
	private String airlineLogo;
	private String seatClass;
	private XMLGregorianCalendar departure;
	private XMLGregorianCalendar arrival;
	private int stops;
	private String price;
	private RemoteAircraft aircraft;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
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
	public XMLGregorianCalendar getDeparture() {
		return departure;
	}
	public void setDeparture(XMLGregorianCalendar departure) {
		this.departure = departure;
	}
	public XMLGregorianCalendar getArrival() {
		return arrival;
	}
	public void setArrival(XMLGregorianCalendar arrival) {
		this.arrival = arrival;
	}
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@XmlElement(name="aircraft")
	public RemoteAircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(RemoteAircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	
}
