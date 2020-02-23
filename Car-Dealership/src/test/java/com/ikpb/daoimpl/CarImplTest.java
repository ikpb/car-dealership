package com.ikpb.daoimpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ikpb.pojo.Car;

public class CarImplTest {
	private static final CarImpl cars = new CarImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	List<Car> testCars = new ArrayList<Car>();
	testCars.add(new Car("Toyoda", "Matrix", 1999, 1500));
	testCars.add(new Car("Honda", "Civic", 1999, 1500));
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGettingACar() {
		List<Car> testCars = new ArrayList<Car>();
		testCars.add(new Car("Toyoda", "Matrix", 1999, 1500));
		testCars.add(new Car("Honda", "Civic", 1999, 1500));
		assertEquals(new Car("Toyoda", "Matrix", 1999, 1500),cars.getSingleCar(testCars.get(7)));

	}
	
	
}
