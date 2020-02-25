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
		return "Car Vin:" + vin + " Car Make:" + make + ", Model:" + model + ", Year:" + year + ", Cost:" + cost;
	}
	
	private String make;
	private String model;
	private int year;
	private double cost;
	private String vin;
	private int offer;
	private int makePayment;
	private double payment;
	private List<Double> payments_ = new ArrayList<Double>();
	private double remainingBalance;
	private double starterBalance;
	private boolean isCarAvaliable;
	private String owner;
	public Map<String, Double> offers = new HashMap<String,Double>();//make a list of customer objects in 
	
	public List<Double> getCarPaymentsMade() {
		System.out.println(this.payments_);
		return payments_;
	}	
	
	
	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public double getPayment() {
		return Math.round(payment);
	}

	public void setPayment(double acceptedOffer) {
		
		
		this.payment = acceptedOffer/60;
	}
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
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
	public Map<String, Double> getOffers() {
		return offers;
	}

	public void setOffers(Map<String, Double> offers) {
		this.offers = offers;
	}
	public void addOffer(String email, Double offer) {
		this.offers.put(email, offer);
	}
	public void clearOffers() {
		this.offers.clear();
	}
	public Double getValueFromOffers(Map<String, Double> offer, String user) {
		double tempDouble = 0;
	for (Map.Entry<String, Double> entry : offers.entrySet()) {
			String key=entry.getKey();
			
		if (key.equals(user)) {
				tempDouble= entry.getValue();
			}
		}
		return tempDouble;
	}
	public String getKeyFromOffer(Map<String, Double> offer, Double value) {
			String tempEmail = "";
		for (Map.Entry<String, Double> entry : offers.entrySet()) {
				if (value.equals(entry.getValue())) {
					tempEmail= entry.getKey();
				}
			}
			return tempEmail;
		}
	
	

	public boolean isCarAvaliable() {
		return isCarAvaliable;
	}

	public void setCarAvaliable(boolean isCarAvaliable) {
		this.isCarAvaliable = isCarAvaliable;
	}
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
	public Car(String vin, String make, String model, int year, double cost, boolean avaliable) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.cost = cost;
		this.vin = vin;
		this.isCarAvaliable = avaliable;

	}
	public Double getValueFromOffer(double offerId, Car carById) {
		return carById.offers.get(offerId);
		
	}
	
}
