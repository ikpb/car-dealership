package com.ikpb.login;

import java.util.Scanner;

import com.ikpb.dao.EmployeeDAO;
import com.ikpb.daoimpl.EmployeeImpl;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;
import com.ikpb.users.Employee;
public class Login {
//take in user input
	public String LoggingIn(){
		Scanner input = new Scanner(System.in);
		
		EmployeeDAO emplDAO = new EmployeeImpl();
		System.out.println("Please enter your Username (email):");
		String email = input.nextLine();
		System.out.println("Please enter your Password:");
		String password = input.nextLine();
		Employee emUser = new Employee(email, password);
		if(emplDAO.getEmployeeByEmail(emUser)) {
			System.out.println("you can proceed to use this programe");
		}else {
			System.out.println("please enter a proper username and password");
		}
		return null;
	}
}
