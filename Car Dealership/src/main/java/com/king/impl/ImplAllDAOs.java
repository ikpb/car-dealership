package com.king.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.king.dao.CarDAO;
import com.king.dao.CustomerDAO;
import com.king.dao.EmployeeDAO;
import com.king.pojo.Car;
import com.king.pojo.Customers;
import com.king.pojo.Employee;
import com.king.pojo.User;

public class ImplAllDAOs implements CarDAO, EmployeeDAO, CustomerDAO{

	private List<User> user = new ArrayList<User>();
	private List<Employee> employee = new ArrayList<Employee>();
	private List<Car> car = new ArrayList<Car>();
	private List<Customers> customers = new ArrayList<Customers>();
	private List<Offers> custOffers = new ArrayList<Offers>();
	
	@Override
	public List<Car> viewCarsOnLot() {
		for(Car x: car) {
			System.out.println(x);
		}
		return car;
	}

	@Override
	public int makeAnOffer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viewCarsIOwn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> viewAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the make of the Car: ");
		String make = scsan.nextLine();
		System.out.println("Enter the model of the Car: ");
		String model = scan.nextLine();
		System.out.println("Enter the year of the Car: ");
		int year = scan.nextInt();
		System.out.println("Enter the cost of the Car: ");
		int cost = scan.nextInt();
		scan.hasNextLine();
		System.out.println("Is there an offer made on the car?");
		int offer = scan.nextInt();
		ArrayList<Integer> offers = new ArrayList<Integer>();
		offers.add(offer);
		Car cars = new Car(make,model,year,cost,offers,list<offers>);
		car.add(cars);
		
	}

	@Override
	public void deleteCar(Car cars) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Which car would you like to remove");
		int deleteCarId = scan.nextInt();
		scan.nextLine();
		for(int i=0; i<car.size(); i++) {
			car.get(deleteCarId-1);
			if(car.get(i).getCarId() == deleteCarId) {
				car.remove(deleteCarId-1);
			}
			
		}
		
	}

	@Override
	public List<Offers> viewOffer() {
		
		return custOffers;
	}

	@Override
	public boolean acceptOrRegejectOffer() {
		custOffers.size()
		return false;
	}

	@Override
	public List<String> listAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> listAllOffesForCar() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public List<Car> getCar() {
		return car;
	}

	public void setCar(List<Car> car) {
		this.car = car;
	}

	public List<Customers> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customers> customers) {
		this.customers = customers;
	}
	
	

}
