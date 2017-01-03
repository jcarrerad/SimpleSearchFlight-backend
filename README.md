# SimpleSearchFlight-backend
Backend Rest Services for Simple Search Flight Demo

To install and run tests:
mvn clean install

To deploy to embedded Tomcat:
mvn tomcat7:run

Airports REST Resource:
http://localhost:8080/flightsearch/airports

Flights REST Resource:
http://localhost:8080/flightsearch/flights?origin={ISO "ALPHA-3 Code}&destination={ISO "ALPHA-3 Code}&date={yyyy-mm-dd}
