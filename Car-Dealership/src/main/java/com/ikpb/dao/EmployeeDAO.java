package com.ikpb.dao;

import com.ikpb.pojo.User;
import com.ikpb.users.Employee;

import java.util.List;

public interface EmployeeDAO {

	void createEmployee(Employee user);
	Employee getEmployeeByEmail(String email, String password);
	void saveEmployee(Employee employee);
	void deleteEmployee(Employee employee);
	
	
	
}
