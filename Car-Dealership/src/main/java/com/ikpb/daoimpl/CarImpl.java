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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ikpb.util.ConnectionFactory;
public class CarImpl implements CarDao{
	
	private static final Logger logger = Logger.getLogger(CarImpl.class);
//	private static String url ="jdbc:postgresql://localhost:5000/dealership";
	//jdbc:postgresql://host:port/database_name
//	private static String username="postgres";
//	private static String password="root";
	public static List<Car> cars = new ArrayList<Car>();
	public CarImpl() {
		super();
		
//		User user1 = new User("johny", "twochains", "john", "123",UserType.CUSTOMER);
//		User user2 = new User("mark", "parez", "mark", "123",UserType.CUSTOMER);
//		cars = new ArrayList<Car>();
//		cars.add(new Car("Toyoda", "Matrix", 1999, 1500));
//		Car car = new Car("Honda","Civic",2015, 3000);
//		cars.add(1, car);
//		car.addOffer(user1, 2500.00);
//		car.addOffer(user2, 3500.00);
//		saveCarList(cars);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsListInitial() {

		List <Car> tempCarList = new ArrayList<Car>();
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM car");
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			while(rs.next()) {
				tempCarList.add(new Car(rs.getString("vin"), rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getInt("price"),rs.getBoolean("isavaliable")));
			}
			
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}cars = tempCarList;
		return tempCarList;
	}
	public List<Car> getAvaliableCarsListInitial() {

		List <Car> tempCarList = new ArrayList<Car>();
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM car where owner is NULL");
			//ps.setString(1,"NULL");
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			while(rs.next()) {
				tempCarList.add(new Car(rs.getString("vin"), rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getInt("price"),rs.getBoolean("isavaliable")));
			}
			
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempCarList;
	}

	public List<Car> getCarsList(List<Car> listOf){
		return listOf;
	}
	public List<Car> getMyCarsList(User user){
		List<Car> carz = new ArrayList<Car>();
			try{
				Connection conn =  ConnectionFactory.getConnection();
				//putting in a native sql query utilizing a prepared statement
				PreparedStatement ps = conn.prepareStatement("Select * from car where owner=?");
				ps.setString(1, user.getEmail());
				ResultSet rs = ps.executeQuery();
				//we are executing the query and storing the result set in 
				//a Resultset
				while(rs.next()) {
					
					carz.add(new Car(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getDouble("price"),rs.getBoolean("isavaliable")));
				
				}
				ps.execute();
				//allows us to execute a query without a result
				conn.close();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return carz;
	}
	public void printCarsList(){
		
		List<Car> tempList = new ArrayList<Car>();
		tempList = getAvaliableCarsListInitial();
		for (Car x: tempList) {
			System.out.println(x);
		}
	}
	@Override
	public void addCar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the vin of the Car: ");
		String vin = scan.nextLine();
		System.out.println("Enter the make of the Car: ");
		String make = scan.nextLine();
		System.out.println("Enter the model of the Car: ");
		String model = scan.nextLine();
		System.out.println("Enter the year of the Car: ");
		int year = scan.nextInt();
		System.out.println("Enter the cost of the Car: ");
		double cost = scan.nextDouble();
		scan.nextLine();
		Car car = new Car(vin,make,model,year,cost,true);
		cars.add(car);
		try{
			Connection conn = ConnectionFactory.getConnection();
			//puttingn in a native sql query utilizing a perpared statemtn
			PreparedStatement ps = conn.prepareStatement("Insert INTO car VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,vin);
			//seting the first question mark to be the name that is passed as
			//paramenter, that belongs to our user object
			ps.setString(2,make);
			//setting the second question mark to be the type that belongs
			//to our user object
			ps.setString(3,model);
			//seting the third question mark to be the name that is passed as
			//paramenter, that belongs to our user object
			ps.setInt(4,year);
			//setting the fouth question mark to be the type that belongs
			//to our user object
			ps.setDouble(5,cost);
			//setting the fifth question mark to be the type that belongs
			//to our user object
			ps.setString(6,null);
			//setting the fifth question mark to be the type that belongs
			//to our user object
			ps.setBoolean(7, true);
			//setting the sixth question mark to be the type that belongs
			//to our user object
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCar(String vin) {
		for(int i=0; i<cars.size(); i++) {
			if(cars.get(i).getVin().matches(vin)) {
				cars.remove(cars.get(i));
			}
			try{
				Connection conn = ConnectionFactory.getConnection();
				//putting in a native sql query utilizing a perpared statemnt
				PreparedStatement ps = conn.prepareStatement("DELETE FROM car WHERE vin=?");
				ps.setString(1, vin);
				ps.executeUpdate();
				//allows us to execute a query without a result
				conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		System.out.println("Car with Vin: "+ vin + " deleted from database");
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
	public Car getCarById(String vin) {
		Car tempCar = new Car();
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("Select * FROM car WHERE vin=?");
			ps.setString(1, vin);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				tempCar = new Car(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getDouble("price"),rs.getBoolean("isavaliable"));
			
			}
			//allows us to execute a query without a result
			conn.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return tempCar;
	}
	
//	@Override
//	public void saveCarList(List<Car> car) {
//		String filename;
//		filename = "car.dat";
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(car);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				oos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	@Override
	public void saveCarList(List<Car> Car, User user) {
		
	}
	@Override public void updateCar(Car car) {
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("UPDATE car SET price=?, owner=?, isavaliable=?  WHERE vin=?");
			ps.setDouble(1, car.getCost());
			ps.setString(2,  car.getOwner());
			ps.setBoolean(3, car.isCarAvaliable());
			ps.setObject(4,  car.getVin());
			ps.executeUpdate();
			//we are executing the query and storing the result set in 
			//a Resultset
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
	
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void clearCarList() {
		for(int i=0; i<cars.size(); i++) {
				cars.remove(cars.get(i));		
		}
	}

	
}
