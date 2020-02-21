package com.ikpb.pojo;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3181062708322226377L;
	//private fields
	
	@Override
	public String toString() {
		return "Car ID:" + this.getId() + " Car Make:" + make + ", Model:" + model + ", Year:" + year + ", Cost:" + cost;
	}
	
	private String make;
	private String model;
	private int year;
	private double cost;
	private int offer;
	private int makePayment;
	private double payment;
	private List<Double> payments_ = new ArrayList<Double>();
	private double remainingBalance;
	private double starterBalance;
	private static int id =0;
	private int carId;
	private boolean isCarAvaliable;
	public Map<User, Double> offers = new HashMap<User,Double>();//make a list of customer objects in 
	
	public List<Double> getCarPaymentsMade() {
		System.out.println(this.payments_);
		return payments_;
	}	
	public double getPayment() {
		return payment;
	}

	public void setPayment(double acceptedOffer) {
		
		
		this.payment = acceptedOffer/60;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance() {
		
		this.remainingBalance = this.makePayment();
	}
	
	public double getStarterBalance() {
		return starterBalance;
	}
	public void setStarterBalance(double starterBalance) {
		this.starterBalance = starterBalance;
	}
	public double makePayment(){
		double paymentmade = 0;
		if(this.getStarterBalance() - this.payment>=0) {
			paymentmade = this.getStarterBalance() - this.payment;
		}
		this.payments_.add(this.payment);
		setStarterBalance(paymentmade);
		return paymentmade;
	}
	public Map<User, Double> getOffers() {
		return offers;
	}

	public void setOffers(Map<User, Double> offers) {
		this.offers = offers;
	}
	public void addOffer(User email, Double offer) {
		this.offers.put(email, offer);
	}
	public void clearOffers() {
		this.offers.clear();
	}
	public Double getValueFromOffers(Map<User, Double> offer, String user) {
		double tempDouble = 0;
	for (Map.Entry<User, Double> entry : offers.entrySet()) {
			User key=entry.getKey();
			
		if (key.getFirstName().contains(user)) {
				tempDouble= entry.getValue();
			}
		}
		return tempDouble;
	}
	public User getKeyFromOffer(Map<User, Double> offer, Double value) {
			User tempUser = new User();
		for (Map.Entry<User, Double> entry : offers.entrySet()) {
				if (value.equals(entry.getValue())) {
					tempUser= entry.getKey();
				}
			}
			return tempUser;
		}
	
	

	public boolean isCarAvaliable() {
		return isCarAvaliable;
	}

	public void setCarAvaliable(boolean isCarAvaliable) {
		this.isCarAvaliable = isCarAvaliable;
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
		this.isCarAvaliable = true;
		setID();
	}
	public Double getValueFromOffer(double offerId, Car carById) {
		return carById.offers.get(offerId);
		
	}
	
}
