package com.ikpb.util;

import java.util.List;
import java.util.Map;

import com.ikpb.daoimpl.CarImpl;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;

public class BusinessLogic {
	CarImpl carImpl = new CarImpl();
public void placeOffer(User user, Car car,boolean activity, double d) {
	if(car.isCarAvaliable()) {
		car.addOffer(user, d);
		System.out.println("Your off of "+d +"was placed on car"+car);
	}
}	
	
public void addCarToUserList(User usez, Car carz){
	usez.getCarList().add(carz);
	carz.setCarAvaliable(false);
	carz.clearOffers();
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
	for (Map.Entry<User, Double> entry : carz.offers.entrySet()) {
	    System.out.println(entry.getKey().getFirstName() + " placed an offer of " + entry.getValue());
	}
}
public void acceptOffer(User firstName,Car carz )
{
	System.out.println("Great! You picked an offer!");
	addCarToUserList(firstName, carz);
	System.out.println("The car has been placed in " + firstName.getFirstName()+"\'s" + " Garage ");
	carImpl.deleteCar(carz.getId());
}

}
	

