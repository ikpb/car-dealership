package com.ikpb.daoimpl;
import com.ikpb.dao.EmployeeDAO;
import com.ikpb.users.Employee;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.ikpb.pojo.User;
public class EmployeeImpl implements EmployeeDAO{
	public void createEmployee(Employee user){
	}
	public Employee getEmployeeByEmail(String email, String password) {
		return null;
	}
	public void saveEmployee(Employee employee) {}
	public void deleteEmployee(Employee employee) {}

}
