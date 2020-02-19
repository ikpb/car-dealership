package com.ikpb.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

public class Login {
//take in user input
	public User LoggingIn(int count, UserType userType){
		Scanner input = new Scanner(System.in);
		//init user class
		UserImpl user = new UserImpl();
		
		User logInInfo = new User();
		
		System.out.println("Please enter your Username (email):");
		String email = input.nextLine();
		System.out.println("Please enter your Password:");
		String password = input.nextLine();

		if(count==0) {
			user.getAllUsers();
		}
			for(int i = 0; i<user.getUserList().size();i++) {
				
				if(email.equals(user.getUserList().get(i).getEmail()) && password.equals(user.getUserList().get(i).getPassword()) && userType.equals(user.getUserList().get(i).getUserType())){
					System.out.println("userfound");
					logInInfo = new User(user.getUserList().get(i).getFirstName(), user.getUserList().get(i).getLastName(), user.getUserList().get(i).getEmail(),user.getUserList().get(i).getUserType());
					break;
				}else {
					logInInfo = new User(UserType.NEW_USER);
				}
			}
	
		return logInInfo;
		
		 //return logInInfo;
	}
}

