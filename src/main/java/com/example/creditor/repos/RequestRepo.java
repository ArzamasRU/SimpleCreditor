package com.example.creditor.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.creditor.domain.Request;

public interface RequestRepo extends JpaRepository<Request, Long> {
	List<Request> findByUsername(String username);
	List<Request> findByCountryAndDateBetween(String country, Date dateStart, Date dateFinish);
}
