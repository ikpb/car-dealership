package com.ikpb.register;

import java.util.Scanner;

import com.ikpb.pojo.User;

public class RegisterUser extends User{
	 public void RegisterUser(){
		 String firstName, lastName, email, password;
			int age;
			 User users = new User();
			 Scanner scan = new Scanner(System.in);
			 users.setUserType(UserType.NEW_USER);
			 int classification = 0;
			 
			 while(getUserType().NEW_USER == users.getUserType() || classification == 0) {
				 System.out.println(users.getUserType());
			System.out.println("Please enter the your First Name, Last Name, Age, Email, Set password");
				firstName = scan.next();
				lastName = scan.next();
				age = scan.nextInt();
				email = scan.next();
				password = scan.next();
				scan.hasNextLine();
			System.out.println("Are you a Customer or Employee: (1: Customer, 2: Employee, 3: To Exit):");
			classification = scan.nextInt();
			
			users.setFirstName(firstName);
			users.setLastName(lastName);
			users.setAge(age);//need to add try catch validate for the int
			users.setEmail(email);
			users.setPassword(password);
			if(classification == 1) {
				users.setUserType(User.UserType.CUSTOMER);
				//store user into a customer file
			}else if(classification==2) {
				users.setUserType(User.UserType.EMPLOYEE);
				//store user into a customer file
			}else if(classification ==3){
				System.out.println("Goodbye");
				break;
			}else {
				System.out.println("Please enter 1, 2 or 3 to exit");
			}
			System.out.println(users.getUserType());
			}System.out.println(users.toString() + "has been registered");
			System.out.println(users.logInInfo());		 
		}
	 }
	
	

