package com.king.pojo;

import java.util.ArrayList;
import java.util.List;

import com.king.pojo.Customers;

public class Car {
	private String make;
	private String model;
	private int year;
	private int cost;
	private static int id =0;
	private int carId;
	public int offer;
	public List<Customers> offers = new ArrayList<Customers>();
	public String getMake() {
		return make;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public static void setId(int id) {
		++id;
		Car.id = id;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		++id;
		this.carId = id;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}
	public List<Customers> getOffers() {
		return offers;
	}
	public void setOffers(List<Customers> offers) {
		this.offers = offers;
	}
	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", year=" + year + ", cost=" + cost + ", carId=" + carId
				+ ", offer=" + offer + ", offers=" + offers + "]";
	}
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Car(String make, String model, int year, int cost, int carId, int offer, List<Customers> offers) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.cost = cost;
		this.carId = carId;
		this.offer = offer;
		this.offers = offers;
	}
}
