package com.ikpb.daoimpl;

import com.ikpb.dao.CarDao;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.Customer;
import com.ikpb.pojo.User;
import com.ikpb.users.Customers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarImpl implements CarDao{

	ArrayList<Car> cars = new ArrayList<Car>();
	ArrayList<Car> carOffers = new ArrayList<Car>();
	ArrayList<Customers> customers = new ArrayList<Customers>();
	ArrayList<Customers> acceptedOffers = new ArrayList<Customers>();
	public CarImpl() {
		
	}
	
	@Override
	public List<Car> getCarsListInitial() {
		String filename;
		filename = "car.dat";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			int num = ois.readInt();
			for(int i=0; i<num;i++) {
				try {
				Object tempObject = (Object)ois.readObject();
				@SuppressWarnings("unchecked")
				Car c = (Car)tempObject;
				
				Car d = new Car(c.getMake(),c.getModel(),c.getYear(),c.getCost(),c.getOffer());
				cars.add(d);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return cars;
	}

	public List<Car> getCarsList(){
		for(Car x: cars) {
			System.out.println(x);
		}
		return cars;
	}
	@Override
	public void addCar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the make of the Car: ");
		String make = scan.nextLine();
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
		Car car = new Car(make,model,year,cost,offers);
		cars.add(car);
	}

	@Override
	public void deleteCar() {
		CarImpl car = new CarImpl();
		car.getCarsList();
		Scanner scan = new Scanner(System.in);
		System.out.println("Which car would you like to remove");
		int deleteCarId = scan.nextInt();
		scan.nextLine();
		for(int i=0; i<cars.size(); i++) {
			if(cars.get(deleteCarId-1).getId() == deleteCarId) {
				cars.remove(deleteCarId-1);
			}
			
		}
		
		System.out.println("Car with Id: "+ deleteCarId + " deleted from database");
	}
	public void saveCarList() {
		String filename;
		filename = "car.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeInt(cars.size());
			for(Car x: cars) {
				oos.writeObject(x);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void clearCarList() {
		for(int i=0; i<cars.size(); i++) {
				cars.remove(cars.get(i));
				
		}
	}

	@Override
	public void makeOffer(String cust, int number, int car) {
		Car a;
		for(int i=0;i<cars.size();i++) {
			if(car == (cars.get(i).getId())) {
			a= new Car(cars.get(i).getMake(),cars.get(i).getModel(),cars.get(i).getYear(),cars.get(i).getCost(),number,cust,car);
			carOffers.add(a);
			}
		}
	}
	
	//save offers()
	public void saveOffers() {
		String filename;
		filename = "offers.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeInt(carOffers.size());
			for(Car x: carOffers) {
				oos.writeObject(x);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public List<Car> getOffer() {
		String filename;
		filename = "offers.dat";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			int num = ois.readInt();
			for(int i=0; i<num;i++) {
				try {
					Car u = (Car)ois.readObject();
				carOffers.add(u);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return carOffers;
		
	}
	public List<Car> getOfferList() {
	
		System.out.println(carOffers);
		return carOffers;
	}

}
