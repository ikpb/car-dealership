package com.ikpb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ikpb.util.ConnectionFactory;
import com.ikpb.Service.UserServiceImpl;
import com.ikpb.daoimpl.CarImpl;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;

public class BusinessLogic {
	CarImpl carImpl = new CarImpl();
	UserImpl users = new UserImpl();
	UserServiceImpl userserve = new UserServiceImpl();
////Allow user to place an offer a database
////////////////////////////////////////////
public String placeOffer(User user, Car car,boolean activity, double d) {
	String tempString = "";
	if(car.isCarAvaliable()) {
		car.addOffer(user.getEmail(), d);
		tempString = "Your offer of "+d +" was placed on "+car;
		storeOfferInDB(user.getEmail(),car.getVin(),d);
	}else {
		tempString ="This car is no longer accepting offers";
	}
	return tempString;
}	
////Set car values and add to database
////////////////////////////////////////////
public User addCarToUserList(User usez, Car carz, double offer){

	//add logger commit
	usez.setAcceptedOffer(offer);
	usez.getCarByVin(carz.getVin()).setPayment(offer);
	usez.getCarByVin(carz.getVin()).setStarterBalance(offer);
	carz.setOwner(usez.getEmail());
	carz.setCarAvaliable(false);
	carz.setCost(offer);
	this.rejectAlloffers(carz);
	carz.setPayment(offer);
	carImpl.updateCar(carz);
	userserve.createUserGarage(usez.getEmail(), carz);
	return usez;
}
////Get the list of cars owned by user from database
////////////////////////////////////////////////////
public List<Car> getUserCarList(String email){
	List<Car> tempCarList = new ArrayList<Car>();
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM car WHERE owner=?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			
			tempCarList.add(new Car(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getDouble("price"),rs.getBoolean("isavaliable")));
			
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}return tempCarList;
}
////Get the cost a car a user is paying on
////////////////////////////////////////////////////
public int getUserCarCost(String email, String vin){

		int j =0;
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT price FROM car WHERE owner=? and vin=?");
		ps.setString(1, email);
		ps.setString(2, vin);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			j=rs.getInt("price");
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}return j;
}
////Get total left to pay on a car
////////////////////////////////////////////////////
public int getCarAmountLeft(String email, String vin){
		int j =0;
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT totalleft FROM payment WHERE userid=? and carid=?");
		ps.setString(1, email);
		ps.setString(2, vin);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			j=rs.getInt("totalleft");
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}return j;
}
////Get the list of monthly payments made by user
////////////////////////////////////////////////////
public List<Integer> getCarPaymentList(String email, String vin){
	List<Integer> j = new ArrayList<Integer>();

try{
	Connection conn =  ConnectionFactory.getConnection();
	//putting in a native sql query utilizing a prepared statement
	PreparedStatement ps = conn.prepareStatement("SELECT paymentamount FROM payment WHERE userid=? and carid=?");
	ps.setString(1, email);
	ps.setString(2, vin);
	ResultSet rs = ps.executeQuery();
	//we are executing the query and storing the result set in 
	//a Resultset
	while(rs.next()) {
		j.add(rs.getInt("paymentamount"));
	}
	
	ps.execute();
	//allows us to execute a query without a result
	conn.close();
}
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}return j;
}
////Get the monthly payment of a car for a user
////////////////////////////////////////////////////
public void printCustomerPaymentList(String email, String vin) {
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT distinct totalleft, paymentamount FROM payment WHERE userid=? and carid=? order by totalleft desc");
		ps.setString(1, email);
		ps.setString(2, vin);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			System.out.println("Customer :"+ email + " made payment of " + rs.getDouble("paymentamount")+ " on his total amount "+ rs.getDouble("totalleft"));
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
////Get a Car that is assigned to a customer by email
////////////////////////////////////////////////////
public List<Car> getCustomerCarListByEmail(String email) {
	List<Car> carz = new ArrayList<Car>();
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("Select * from car where owner=?");
		ps.setString(1, email);
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
	}
	return carz;
	
}
////Prints the list of Cars that are assigned to a customer by email
///////////////////////////////////////////////////////////////////
public void printCustomerCarList(String email) {
	List<Car>a=getCustomerCarListByEmail(email);
	List<Car> tempList = new ArrayList<Car>();
	tempList = a;
	for(Car x: tempList) {
		System.out.println(x);
	}
}
////Get the monthly payment of a car for a user
////////////////////////////////////////////////////
public int myMonthlyPayment(String email, String vin) {
	int j =0;
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT distinct paymentamount FROM payment WHERE userid=? and carid=?");
		ps.setString(1, email);
		ps.setString(2, vin);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			j=rs.getInt("paymentamount");
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}return j;
}
////Get the list of cars owned by user from database
////////////////////////////////////////////////////
public String makeMonthlyPayment(String email, String vin) {
	int j =0;
	int a =getCarAmountLeft(email, vin);
	int b = myMonthlyPayment(email, vin);
	 int totalLeftAfterPayment = a-b;
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("insert into payment (userid, totalleft,paymentamount, carid)"
				+ " values (?,?,?,?)");
		ps.setString(1, email);
		ps.setInt(2, totalLeftAfterPayment);
		ps.setInt(3, b);
		ps.setString(4, vin);
		int rs = ps.executeUpdate();
		//we are executing the query and storing the result set in 
		//a Resultset
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} String e = "Your payment was made successfully";
	return e;
}
////User get to see what car they own
////////////////////////////////////////////////////
public void viewOwnedCars(User usez) {
//	List<Car> myList = usez.getCarList();
//	for(Car x: myList) {
//		System.out.println(x);
	try{
		Car tempCar;
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM car WHERE owner=?");
		ps.setString(1, usez.getEmail());
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			
			tempCar  = new Car(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getDouble("price"),rs.getBoolean("isavaliable"));
		System.out.println(tempCar.toString());
			
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
////Rejects offers on a car after the car as been bought
////Call stored procedure to change offers on car to false
////In database
////////////////////////////////////////////////////////
public void rejectAlloffers(Car carz) {
	carz.clearOffers();
	try{Connection conn =  ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		
		String sql = "call updateValidOffer(?)";
		
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement call = conn.prepareCall(sql);
		call.setString(1, carz.getVin());
		call.executeUpdate();
		//we are executing the query and storing the result set in 
		//a Resultset
		
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
////Allow employee to see the offers on a car
////////////////////////////////////////////////////
public void viewAllOffersOnCar(String vin) {
//	for (Map.Entry<String, Double> entry : carz.offers.entrySet()) {
//	    System.out.println("Offer User Id:"+entry.getKey().toString() + " placed an offer of " + entry.getValue());
//	;
//	}
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("Select userid, carid, offeramount from offer where carid =?");
		ps.setString(1, vin);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			System.out.println("User Id: " + rs.getString("userid") + " Car Id: "+ rs.getString("carid") + " Offer Amount: " + rs.getDouble("offeramount"));
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
////Employee gets to accept offer.
//////////////////////////////////////////////////////
public User acceptOffer(User firstName,Car carz, double offer )
{
	User tempUser = new User();
	System.out.println(carz);
	System.out.println("Great! You picked an offer!");
	tempUser = addCarToUserList(firstName, carz, offer);
	System.out.println("The car has been placed in " + firstName.getFirstName()+"\'s" + " Garage ");

	return tempUser;
}

////Stores the accepted offer into database
////////////////////////////////////////////////////
public void storeOfferInDB(String email, String vin, double amount) {
	try{
		Connection conn = ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("Insert into offer(userid,carid,offeramount,validoffer) values (?,?,?,?)");
		ps.setString(1, email);
		ps.setString(2, vin);
		ps.setDouble(3, amount);
		ps.setBoolean(4, true);
		ps.executeUpdate();
		//allows us to execute a query without a result
		conn.close();
}catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
////Grabs and stores offers in a map list from database
////////////////////////////////////////////////////////
public Map<String,Double> getOffersFromDB(Car carz) {
	Map<String,Double> dbOffers = new HashMap<>();
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//puttingn in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM offer WHERE carid=?");
		ps.setString(1, carz.getVin());
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			if(rs.getString("carid").equals(carz.getVin())) {
			dbOffers.put(rs.getString("userid"), rs.getDouble("offeramout"));
		}
			
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} return dbOffers;
}
public double getOneOffersFromDB(String vin, String userId) {
	double tempNum = 0.0;
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//puttingn in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("SELECT offeramount FROM offer WHERE carid=? and userid =?");
		ps.setString(1, vin);
		ps.setString(2, userId);
		ResultSet rs = ps.executeQuery();
		//we are executing the query and storing the result set in 
		//a Resultset
		while(rs.next()) {
			tempNum=rs.getDouble("offeramount");
		}
		
		ps.execute();
		//allows us to execute a query without a result
		conn.close();
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} return tempNum;
}
}

	

