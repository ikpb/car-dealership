package com.king.dao;

import java.util.List;

import com.king.pojo.Car;

public interface CustomerDAO {

	List<Car> viewCarsOnLot();
	int makeAnOffer();
	void viewCarsIOwn();
	List<String> viewAllPayments();
}
