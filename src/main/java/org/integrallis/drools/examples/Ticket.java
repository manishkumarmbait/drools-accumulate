package org.integrallis.drools.examples;

public class Ticket {
	private Person driver;
	private Double amount;
	private String reason;
	private String driverType;
	private Boolean friendsOfTheSheriff;

	public Ticket(Person driver, Double amount, String reason, Boolean friendsOfTheSheriff, String driverType) {
		super();
		this.driver = driver;
		this.amount = amount;
		this.reason = reason;
		this.friendsOfTheSheriff = friendsOfTheSheriff;
		this.driverType = driverType;
	}

	public Person getDriver() {
		return driver;
	}

	public Double getAmount() {
		return amount;
	}

	public String getReason() {
		return reason;
	}

	public Boolean getFriendsOfTheSheriff() {
		return friendsOfTheSheriff;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
}
