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
	
public User addCarToUserList(User usez, Car carz, double offer){

	//add logger commit
	usez.setAcceptedOffer(offer);
	usez.getCarByVin(carz.getVin()).setPayment(offer);
	usez.getCarByVin(carz.getVin()).setCost(offer);
	usez.getCarByVin(carz.getVin()).setStarterBalance(offer);
	carz.setOwner(usez.getEmail());
	carz.setCarAvaliable(false);
	carz.clearOffers();
	carImpl.updateCar(carz);
	userserve.createUserGarage(usez.getEmail(), carz);
	return usez;
}
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
}public int getUserCarCost(String email, String vin){

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
		j.add(rs.getInt("totalleft"));
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
public String makeMonthlyPayment(String email, String vin) {
	String j ="";
	try{
		Connection conn =  ConnectionFactory.getConnection();
		//putting in a native sql query utilizing a prepared statement
		PreparedStatement ps = conn.prepareStatement("insert into payment ()serid=? and carid=?");
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

public void rejectAlloffers(Car carz) {
	carz.clearOffers();
	
}
public void viewAllOffersOnCar(Car carz) {
	for (Map.Entry<String, Double> entry : carz.offers.entrySet()) {
	    System.out.println("Offer User Id:"+entry.getKey().toString() + " placed an offer of " + entry.getValue());
	;
	}
}
public User acceptOffer(User firstName,Car carz, double offer )
{
	User tempUser = new User();
	System.out.println("Great! You picked an offer!");
	tempUser = addCarToUserList(firstName, carz, offer);
	System.out.println("The car has been placed in " + firstName.getFirstName()+"\'s" + " Garage ");

	return tempUser;
}
public void viewAllPayments(List<User> users) {
	for(int k=0; k<users.size();k++) {
		
		for(int j=0; j<users.get(k).getCarList().size();j++) {
			
		
		
//		for(int j=0; j<users.getCarList().get(k).getCarList().size();k++) {
//			for(int l = 0;l<users.getCarList().get(k).getCarList().get(j).paymentsMade.size();l++) {
//				for(int m =0;m<users.getCarList().get(k).getCarList().get(j).paymentsMade.get(l); l++) {
//					System.out.println(users.getCarList().get(k).getFirstName() + " " + users.getCarList().get(k).getCarList().get(j).paymentsMade.get(l));
//				}
			}
		}
	}
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

}

	

