package com.ikpb.dao;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;

import java.util.List;

public interface CarDao {
	List<Car> getCarsListInitial();
	public List<Car>getCarsList();
	public List<Car>getMyCarsList(User user);
	public void addCar();
	public void deleteCar(String vin);
	public void updateCar(Car car);
	public void saveCarList(List<Car> car, User user);


}
