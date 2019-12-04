package com.example.creditor.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double summ;
	private Long userId;
	private String username;
	private String name;
	private String surname;
	private String country;
	private boolean approved;

	@Temporal(TemporalType.DATE)
	private Date term;

	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;

	public Request() {
	}

	public Request(User user, Date term, Double summ, Date date) {
		super();
		this.user = user;
		this.username = user.getUsername();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.country = user.getCountry();
		this.userId = user.getId();
		this.term = term;
		this.summ = summ;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSumm() {
		return summ;
	}

	public void setSumm(Double summ) {
		this.summ = summ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getTerm() {
		return term;
	}

	public void setTerm(Date term) {
		this.term = term;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
