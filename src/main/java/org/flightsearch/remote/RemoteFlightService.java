package org.flightsearch.remote;

import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.flightsearch.remote.model.RemoteFlightList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class RemoteFlightService implements IRemoteFlightService{

	@Autowired
	DummyFlightFactory dummyFlightFactory;
	
	public Document fetchFlights(String origin, String destination, Date departure){
		Document document = null;
		try {
			// Create the JAXBContext
			JAXBContext jc = JAXBContext.newInstance(RemoteFlightList.class);

			// Create the Object
			RemoteFlightList flights = dummyFlightFactory.createRemoteFlightList(origin, destination, departure);

			// Create the Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
			
			// Marshal the Object to a Document
			Marshaller marshaller = jc.createMarshaller();
			marshaller.marshal(flights, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return document;
	}
	
	 
	 
	

}
