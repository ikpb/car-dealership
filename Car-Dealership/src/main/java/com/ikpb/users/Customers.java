package com.ikpb.users;

import com.ikpb.pojo.User;

public class Customers extends User{
	
	//view payments
	private int remainingPayments() {
		return 0;
	}
	//view cars owned
	private String viewCarsOwned() {
		return null;
	}
	//view cars on a lot
	public String viewCarsOnLot() {
		return null;
	}
	//make an offer on a care
	public int makeOfferOnCar() {
		return 0;
	}
	

	public Customers(String firstName, String lastName, int age, UserType user) {
		this();
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		setUserType(user);
		
	}

	public Customers() {
		super();
	}

}
