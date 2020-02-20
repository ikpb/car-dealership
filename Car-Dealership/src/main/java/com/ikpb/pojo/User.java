package com.ikpb.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2622944054219654240L;
	public enum UserType{
		CUSTOMER, EMPLOYEE, NEW_USER;
	}
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserType usertype;
	private int offer;
	private int makePayment;
	private List<Car> car = new ArrayList<Car>();
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserType(UserType userType){
		this.usertype = userType; 
	}
	public UserType getUserType() {
		return this.usertype;
	}
	public void addCartoList(Car car) {
		this.car.add(car);
	}
	public void viewCarsOwn() {
		for(int i =0; i<car.size();i++) {
			System.out.println(car);
		}
	}
	public void getCarPaymentsMade(Car car) {
		System.out.println(car.paymentsMade);
	}
	public List<Car> getCarList() {
		return car;
	}
	public void setCar(List<Car> car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "User First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email
				+ "password: " + password + "and registered as an: " + usertype ;
	}

	
	public String logInInfo(String email, String password) {
		return email + " " + password;
	}
//	public User(String firstName, String lastName, int age, String email, String password) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.age = age;
//		this.email = email;
//		this.password = password;
//	}
//	

	public User(String firstName, String lastName, String email,String password, UserType usertype) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
	}
	public User(String firstName, String lastName, String email, UserType usertype) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.usertype = usertype;
	}
	public User(UserType usertype) {
		this.usertype= usertype;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
