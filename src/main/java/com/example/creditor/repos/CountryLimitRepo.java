package com.example.creditor.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditor.domain.CountryLimit;

public interface CountryLimitRepo extends JpaRepository<CountryLimit, Long> {
	CountryLimit findByCountry(String country);
}
