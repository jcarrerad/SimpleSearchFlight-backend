package org.flightsearch.remote.proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.flightsearch.domain.Flight;
import org.flightsearch.remote.FlightParserHandler;
import org.flightsearch.remote.IRemoteFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

@Component
public class RemoteFlightSearch implements IRemoteFlightSearch {

	@Autowired
	IRemoteFlightService iRemoteFlightService;
	
	public List<Flight> search(String origin, String destination,
			Date flightDate) {
		
		Document xmlDoc = iRemoteFlightService.fetchFlights(origin, destination, flightDate);
		
		return parseXml(xmlDoc);
	}
	
	 private List<Flight> parseXml(Document xmlDoc)
	    {
		 List<Flight> flights = new ArrayList<Flight>();
			 
		 try
	        {
			 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				Source xmlSource = new DOMSource(xmlDoc);
				Result outputTarget = new StreamResult(outputStream);
				TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
				InputStream in = new ByteArrayInputStream(outputStream.toByteArray());

				FlightParserHandler handler = new FlightParserHandler();
	 
	            XMLReader parser = XMLReaderFactory.createXMLReader();
	 
	            //Register handler with parser
	            parser.setContentHandler(handler);
	 
	            //Create an input source from the XML input stream
	            InputSource source = new InputSource(in);
	 
	            parser.parse(source);
	            flights = handler.getFlights();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        return flights;
	    }

}
