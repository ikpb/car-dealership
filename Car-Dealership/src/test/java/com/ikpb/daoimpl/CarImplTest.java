package com.ikpb.daoimpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ikpb.pojo.Car;

public class CarImplTest {
	static List<Car> cars = new ArrayList<Car>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		cars.add(new Car("123","ford","runner",1996,20000,true));
		cars.add(new Car("145","honda","civic",2000,1500,true));
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
	public void test() {
		CarImpl implCar = new CarImpl();
		List<Car> carz= new ArrayList<Car>();
		List<Car> actual = implCar.getCarsList(carz);
		carz.add(new Car("123","ford","runner",1996,20000,true));
		carz.add(new Car("145","honda","civic",2000,1500,true));
		List<Car> expected = cars;
		assertEquals(expected,actual);
	}

}
