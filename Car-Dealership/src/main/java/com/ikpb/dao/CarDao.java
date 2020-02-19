package com.ikpb.dao;
import com.ikpb.pojo.Car;

import java.util.List;

public interface CarDao {
	List<Car> getCarsListInitial();
	public List<Car>getCarsList();
	public void addCar();
	public void deleteCar(int id);
	public void saveCarList();


}
