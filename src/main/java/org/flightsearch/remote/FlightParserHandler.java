package org.flightsearch.remote;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.flightsearch.domain.Flight;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FlightParserHandler extends DefaultHandler {
	//This is the list which shall be populated while parsing the XML.
    private List<Flight> flights = new ArrayList<Flight>();
 
    //As we read any XML element we will push that in this stack
    private Stack<String> elementStack = new Stack<String>();
    
    private static final String FLIGHT_ELEMENT = "flight";
 
    //As we complete one user block in XML, we will push the User instance in userList
    private Stack<Flight> objectStack = new Stack<Flight>();
 
   
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        //Push it in element stack
        this.elementStack.push(qName);
 
        //If this is start of 'flight' element then prepare a new Flight instance and push it in object stack
        if (FLIGHT_ELEMENT.equals(qName))
        {
            //New User instance
            Flight flight = new Flight();
 
            this.objectStack.push(flight);
        }
    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        //Remove last added  element
        this.elementStack.pop();
 
        //User instance has been constructed so pop it from object stack and push in userList
        if (FLIGHT_ELEMENT.equals(qName))
        {
            Flight object = this.objectStack.pop();
            this.flights.add(object);
        }
    }
 
    /**
     * This will be called everytime parser encounter a value node
     * */
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String value = new String(ch, start, length).trim();
 
        if (value.length() == 0)
        {
            return; // ignore white space
        }
        
        Flight flight = null;
 
        //handle the value based on to which element it belongs
        if ("airline".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setAirline(value);
        }
        else if ("airlineLogo".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setAirlineLogo(value);
        }
        else if ("flightCode".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setFlightCode(value);
        }
        else if ("origin".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setOrigin(value);
        }
        else if ("destination".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setDestination(value);
        }
        else if ("seatClass".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setSeatClass(value);
        }
        else if ("stops".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setStops(Integer.valueOf(value));
        }
        else if ("price".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setPrice(new BigDecimal(value));
        }
        else if ("departure".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setDeparture(convertStringToDate(value));
        }
        else if ("arrival".equals(currentElement()))
        {
        	flight = (Flight) this.objectStack.peek();
        	flight.setArrival(convertStringToDate(value));
        }
    }
    
    
    private Date convertStringToDate(String strDate){
    			String format = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    			Date result = null;
    			
    			GregorianCalendar cal = new GregorianCalendar();
    		     try {
					cal.setTime(new SimpleDateFormat(format).parse(strDate));
					 XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
					 result = calendar.toGregorianCalendar().getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
    		     
    	        return result;
    	    }
 
    /**
     * Utility method for getting the current element in processing
     * */
    private String currentElement()
    {
        return this.elementStack.peek();
    }
 
    //Accessor for userList object
    public List<Flight> getFlights()
    {
        return flights;
    }
    
    
    
}
