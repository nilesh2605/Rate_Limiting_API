package com.ratelimit.ratelimit.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private long id;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNoOfHits() {
		return noOfHits;
	}
	public void setNoOfHits(int noOfHits) {
		this.noOfHits = noOfHits;
	}
	public Timestamp getFirsthit() {
		return firsthit;
	}
	public void setFirsthit(Timestamp firsthit) {
		this.firsthit = firsthit;
	}
	private int noOfHits;
	private Timestamp firsthit;
	public User(long id, int noOfHits, Timestamp firsthit) {
		super();
		this.id = id;
		this.noOfHits = noOfHits;
		this.firsthit = firsthit;
	}
}
