package com.ikpb.daoimpl;

import com.ikpb.dao.CarDao;
import com.ikpb.pojo.Car;
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
		cars = new ArrayList<Car>();
		cars.add(new Car("Toyoda", "Matrix", 1999, 2000));
	}
	
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
			int num = ois.readInt();
			for(int i=0; i<num;i++) {
				try {
				Object tempObject = (Object)ois.readObject();
				@SuppressWarnings("unchecked")
				Car c = (Car)tempObject;
				cars.add(c);
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
		double cost = scan.nextInt();
		scan.hasNextLine();
		Car car = new Car(make,model,year,cost);
		cars.add(car);
	}

	@Override
	public void deleteCar() {
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
	public Car getSingleCar(Car car) {
		Car c = new Car();
		for(int k = 0; k<cars.size();k++) {
		if(cars.get(k).equals(car)) {
			c = cars.get(k);
			}
		}
		return c;
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
}
