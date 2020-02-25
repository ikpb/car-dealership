package com.ikpb.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.ikpb.Service.UserService;
import com.ikpb.Service.UserServiceImpl;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

public class Login {
	private static final Logger logger = Logger.getLogger(UserImpl.class);
//	private static String url ="jdbc:postgresql://localhost:5000/dealership";
	//jdbc:postgresql://host:port/database_name
//	private static String username="postgres";
//	private static String password="root";
//take in user input
	
	
	private static UserService uService = new UserServiceImpl();
	public User LoggingIn( UserType userType){
		int j=1;
		User tempUser = new User(); 
		while(j != 0) {
		Scanner input = new Scanner(System.in);
		logger.info("User inputing information ");
		System.out.println("Please enter your Username (email):");
		String email = input.nextLine();
		System.out.println("Please enter your Password:");
		String password = input.nextLine();
		tempUser = uService.loginUser(email, password);
		if (tempUser != null) {
			System.out.println("Welcom Back :" + tempUser.getFirstName());
			j=0;
			logger.info("user" + tempUser.getFirstName() + "Succesfully logged in");
		}else {
			System.out.println(" Incorrect username/password. Please try again.");
			logger.info("user with did not log in successfully");
			j=1;
		}
		
		
	}return tempUser;
	}
	
}

