package org.flightsearch.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.flightsearch.domain.Airport;

@Entity
@Table(name="airport")
public class AirportEntity {

	/**
	 * IATA airport code
	 */
	@Id
	private String code;
	@Column
	private String name;
	/**
	 * ISO "ALPHA-3 Code
	 */
	@Column
	private String country;
	
	
	
	public AirportEntity(String code, String name, String country) {
		super();
		this.code = code;
		this.name = name;
		this.country = country;
	}

	public AirportEntity(Airport airport){
		this.code = airport.getId();
		this.name = airport.getName();
		this.country = airport.getCountry();
	}

	public AirportEntity() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirportEntity other = (AirportEntity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	
}