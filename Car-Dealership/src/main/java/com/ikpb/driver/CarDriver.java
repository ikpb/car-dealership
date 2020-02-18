package com.ikpb.driver;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ikpb.daoimpl.CarImpl;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.Customer;

public class CarDriver {
	static Logger logger = Logger.getLogger(CarDriver.class);
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		logger.debug("Log4j appender configuration is successful");
		CarImpl cars = new CarImpl();
		cars.getCarsListInitial();
		cars.addCar();
		cars.deleteCar();
		cars.saveCarList();
	}

}
