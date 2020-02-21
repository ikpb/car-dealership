package com.ikpb.util;

import java.util.List;
import java.util.Map;

import com.ikpb.daoimpl.CarImpl;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;

public class BusinessLogic {
	CarImpl carImpl = new CarImpl();
	UserImpl users = new UserImpl();
public Car placeOffer(User user, Car car,boolean activity, double d) {
	if(car.isCarAvaliable()) {
		car.addOffer(user, d);
		System.out.println("Your offer of "+d +" was placed on "+car);
	}
	return car;
}	
	
public User addCarToUserList(User usez, Car carz){
	usez.setCar(usez.getCarList());
	usez.getCarList();
	System.out.println(usez + "add to car userList");
	usez.addCartoList(carz);
	carz.setCarAvaliable(false);
	carz.clearOffers();
	System.out.println(usez.getCarList() + "addcartoUserList");
	return usez;
}
public void viewOwnedCars(User usez) {
	List<Car> myList = usez.getCarList();
	for(Car x: myList) {
		System.out.println(x);
	}
}
public void rejectAlloffers(Car carz) {
	carz.clearOffers();
}
public void viewAllOffersOnCar(Car carz) {
	int i=0;
	for (Map.Entry<User, Double> entry : carz.offers.entrySet()) {
	    System.out.println("Offer Id: "+i+" "+entry.getKey().getFirstName() + " placed an offer of " + entry.getValue());
	i++;
	}
}
public User acceptOffer(User firstName,Car carz )
{
	User tempUser = new User();
	System.out.println("Great! You picked an offer!");
	tempUser = addCarToUserList(firstName, carz);
	System.out.println("The car has been placed in " + firstName.getFirstName()+"\'s" + " Garage ");
	System.out.println(tempUser + "tempuser being passed");
	System.out.println(tempUser.getCarList() + "addcartoUserList 2");
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
}

	

