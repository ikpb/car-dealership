package com.ikpb.daoimpl;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarImplTest {
	private static final CarImpl cars = new CarImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void shouldTakeUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter year(yyyy):");
        int year = sc.nextInt();
        assertEquals(cars, cars.addCar());
        sc.close();
	}

}
