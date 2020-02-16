package com.ikpb.dao;
import com.ikpb.pojo.Car;
import java.util.List;

public interface CarDao {
	List<Car> getAllCars();
	Car getCarsByYear(int year);
	void addCar(Car car);
	void deleteCar(Car car);

}
