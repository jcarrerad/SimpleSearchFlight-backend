package org.flightsearch.domain;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "airport")
public class Airport {

	private String id;
	private String code;
	private String name;
	private String country;
	
	
	
	public Airport(String code, String name, String country) {
		super();
		this.id = code;
		this.code = code;
		this.name = name;
		this.country = country;
	}

	public Airport(){
		super();
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String code) {
		this.id = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
