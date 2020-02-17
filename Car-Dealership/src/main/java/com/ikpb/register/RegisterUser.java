package com.ikpb.register;

import java.util.Scanner;

import com.ikpb.dao.EmployeeDAO;
import com.ikpb.daoimpl.EmployeeImpl;
import com.ikpb.pojo.User;
import com.ikpb.users.Employee;

public class RegisterUser extends User{
	 public void RegisterUsers(){
		 String firstName, lastName, email, password;
			int age;
			 User users = new User();
			 Scanner scan = new Scanner(System.in);
			 users.setUserType(UserType.NEW_USER);
			 int classification = 0;
			 
			 EmployeeDAO emplDAO = new EmployeeImpl();
			 while(getUserType().NEW_USER == users.getUserType() || classification == 0) {
			 System.out.println("Are you a Customer or Employee: (1: Customer, 2: Employee, 3: To Exit):");
					classification = scan.nextInt();
			System.out.println("Please enter the your First Name, Last Name, Age, Email, Set password");
				firstName = scan.next();
				lastName = scan.next();
				age = scan.nextInt();
				email = scan.next();
				password = scan.next();
				scan.hasNextLine();
			
			if(classification == 1) {
				users.setUserType(User.UserType.CUSTOMER);
				//store user into a customer file
			}else if(classification==2) {
				users.setUserType(User.UserType.EMPLOYEE);
				//store user into a customer file
				Employee emUser = new Employee(firstName,lastName,age,email,password,UserType.EMPLOYEE);
				emplDAO.createEmployee(emUser);
				System.out.println(emUser.toString() + "has been registered");
				
			}else if(classification ==3){
				System.out.println("Goodbye");
				break;
			}else {
				System.out.println("Please enter 1, 2 or 3 to exit");
			}
			System.out.println(users.getUserType());
			}
			System.out.println(users.logInInfo());		 
		}
	 }
	
	

