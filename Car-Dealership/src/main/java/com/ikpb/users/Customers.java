package com.ikpb.users;
import java.util.Scanner;

import com.ikpb.dao.CustomersDAO;
import com.ikpb.daoimpl.*;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.Customer;

import com.ikpb.pojo.User;

public class Customers extends User implements CustomersDAO {
	CarImpl car = new CarImpl();
	Car carOffer = new Car();
	Customers customer = new Customers();
	
	//view payments
	public int remainingPayments() {

		return 0;
	}
	//view cars owned
	public String viewCarsOwned() {
		return null;
	}
	//view cars on a lot
	public String viewCarsOnLot() {
		car.getCarsList();
		return null;
	}
	//make an offer on a care
	public void makeOfferOnCar() {
	Scanner scan = new Scanner(System.in);
	System.out.println("Which car would you like to Place an Offer on?(By Id):");
	int CarId = scan.nextInt();
	System.out.println("Would you like to Place an Offer on?:");
	int offerAmount = scan.nextInt();
	scan.nextLine();
	for(int i=0; i<car.getCarsList().size(); i++) {
		if(car.getCarsList().get(i).getId() == CarId) {
			car.getCarsList().get(i).setOffer(offerAmount);
		}
		}
		
		
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
