# SimpleSearchFlight-backend
Backend Rest Services for Simple Search Flight Demo

## Prerequisites

You will need the following things properly installed on your computer.
* [Java 7] (www.oracle.com) 
* [Maven] (https://maven.apache.org/)
* [MySQL] (https://dev.mysql.com)

## Build and Tests
* mvn clean install

## Running / Development
* mvn tomcat7:run
* Visit your app at http://localhost:8080

## Rest Resources
Airports:
* http://localhost:8080/flightsearch/airports

Flights:
* http://localhost:8080/flightsearch/flights?origin={ISO "ALPHA-3 Code}&destination={ISO "ALPHA-3 Code}&date={yyyy-mm-dd}
