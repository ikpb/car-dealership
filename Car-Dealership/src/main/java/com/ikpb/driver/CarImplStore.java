package com.ikpb.driver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.ikpb.daoimpl.CarImpl;
import com.ikpb.pojo.Car;

public class CarImplStore {
	CarImpl car = new CarImpl();
	String filename;
	filename = "car.dat";
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;
	try {
		fos = new FileOutputStream(filename);
		oos = new ObjectOutputStream(fos);
		oos.writeInt(cars.size());
		for(Car x: cars) {
			oos.writeObject(x);
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
