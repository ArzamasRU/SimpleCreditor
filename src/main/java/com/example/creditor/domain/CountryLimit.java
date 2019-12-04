package com.example.creditor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountryLimit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String country;
	private Integer countryLimit;

	public CountryLimit() {
	}

	public CountryLimit(String country, Integer limit) {
		super();
		this.country = country;
		this.countryLimit = limit;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getLimit() {
		return countryLimit;
	}
	public void setLimit(Integer limit) {
		this.countryLimit = limit;
	}

}
