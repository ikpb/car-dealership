package com.king.dao;

import java.util.List;

import com.king.pojo.Customers;

public interface CarDAO {

List<String> listAllCars();

List<Customers> listAllOffesForCar();


}
