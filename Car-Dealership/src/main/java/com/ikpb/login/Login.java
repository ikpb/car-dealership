package com.ikpb.login;

import java.util.ArrayList;
import java.util.List;
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
		List<User> use = new ArrayList<User>();
		
		User logInInfo = new User();
		
		System.out.println("Please enter your Username (email):");
		String email = input.nextLine();
		System.out.println("Please enter your Password:");
		String password = input.nextLine();

			for(int i = 0; i<user.getUserList().size();i++) {
				
				if(email.equals(user.getUserList().get(i).getEmail()) && password.equals(user.getUserList().get(i).getPassword()) && userType.equals(user.getUserList().get(i).getUserType())){
					System.out.println("userfound");
					logInInfo = user.getUser(user.getUserList().get(i));
					break;
				}else {
					logInInfo = new User(UserType.NEW_USER);
					
				}
			}System.out.println("Your Username and Password are invalid, please try again.");
			System.out.println("If this is your first time, please register with us!");
	
		return logInInfo;
		
		 //return logInInfo;
	}
}

