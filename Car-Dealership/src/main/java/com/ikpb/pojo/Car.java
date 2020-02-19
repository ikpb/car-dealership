package com.ikpb.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ikpb.daoimpl.Offers;
import com.ikpb.users.Customers;

public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3181062708322226377L;
	//private fields
	
	@Override
	public String toString() {
		return this.getId() + " Car Make:" + make + ", Model:" + model + ", Year:" + year + ", Cost:" + cost;
	}
	
	private String make;
	private String model;
	private int year;
	private double cost;
	private static int id =0;
	private int carId;
	private double acceptedOffer;
	private double payment;
	private double remainingBalance;
	public List<Customer> offers = new ArrayList<Customer>();//make a list of customer objects in 
	
	public List<Double> paymentsMade = new ArrayList<>();
	public List<Double> getPaymentsMade() {
		return paymentsMade;
	}

	public void setPaymentsMade(List<Double> paymentsMade) {
		this.paymentsMade = paymentsMade;
	}

	public List<Customer> getOffer() {
		return offers;
	}
	
	public double getAcceptedOffer() {
		return acceptedOffer;
	}

	public void setAcceptedOffer(double acceptedOffer) {
		this.acceptedOffer = acceptedOffer;
	}

	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance() {
		
		this.remainingBalance = this.makePayment();
	}

	public void setOffers(List<Customer> offers) {
		this.offers = offers;
	}

	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getId() {
		return carId;
	}
	private void setID() {
		id++;
		this.carId = id;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double acceptedOffer) {
		
		
		this.payment = acceptedOffer/60;
	}
	public double makePayment(){
		double paymentmade = 0;
		if(this.remainingBalance - this.payment>=0) {
			paymentmade = this.remainingBalance - this.payment;
		}
		this.paymentsMade.add(payment);
		return paymentmade;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (cost != other.cost)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String make, String model, int year, double cost) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.cost = cost;
		setID();
	}
	
}
