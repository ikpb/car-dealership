package com.ikpb.daoimpl;

import com.ikpb.dao.CarDao;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

import java.util.logging.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
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
	
	private static final Logger logger = Logger.getLogger(CarImpl.class);
	List<Car> cars = new ArrayList<Car>();
	public CarImpl() {
		super();
		User user1 = new User("Toyoda", "Matrix", "bob", "123",UserType.CUSTOMER);
		User user2 = new User("Honda", "Civic", "John", "123",UserType.CUSTOMER);
		cars = new ArrayList<Car>();
		cars.add(new Car("Toyoda", "Matrix", 1999, 2000));
		Car car = new Car("tomota","daytrix",2000, 3000);
		cars.add(1, car);
		car.addOffer(user1, 2500.00);
		car.addOffer(user2, 3500.00);
		getCarsListInitial();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsListInitial() {
		String filename;
		filename = "car.dat";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		logger.info("opening up a fileinputstring, objectinput");
		try {
			logger.warn("trying to complete the writing to a file");
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			try {
				cars = (List<Car>) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		double cost = scan.nextInt();
		scan.hasNextLine();
		Car car = new Car(make,model,year,cost);
		cars.add(car);
	}

	@Override
	public void deleteCar(int id) {
		for(int i=0; i<cars.size(); i++) {
			if(cars.get(i).getId() == id) {
				cars.remove(cars.get(i));
			}
			
		}
		
		System.out.println("Car with Id: "+ id + " deleted from database");
	}
	public Car getSingleCar(Car car) {
		Car c = new Car();
		for(int k = 0; k<cars.size();k++) {
		if(cars.get(k).equals(car)) {
			c = cars.get(k);
			}
		}
		return c;
	}
	public Car getCarById(int id) {
		Car tempCar = new Car();
		for(int l=0; l<cars.size(); l++) {
			if(cars.get(l).getId() == id) {
				tempCar = cars.get(l);
			}
		}return tempCar;
	}
	
	@Override
	public void saveCarList(List<Car> car) {
		String filename;
		filename = "car.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(car);
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

	
}
