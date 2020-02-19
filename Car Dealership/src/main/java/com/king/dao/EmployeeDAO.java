package com.king.dao;
import java.util.List;

import com.king.pojo.Car;
public interface EmployeeDAO{

void addCar();

void deleteCar(Car car);

String viewOffer();

boolean acceptOrRegejectOffer();

List<String> viewAllPayments();


}
