package com.ikpb.dao;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.Customer;
import com.ikpb.pojo.User;
import com.ikpb.users.Customers;

import java.util.List;

public interface CarDao {
	List<Car> getCarsListInitial();
	public List<Car>getCarsList();
	public void addCar();
	public void deleteCar();
	public void saveCarList();


}
