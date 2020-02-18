package com.ikpb.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

public class Login {
//take in user input
	public User LoggingIn(UserImpl usesr, int count, UserType userType){
		Scanner input = new Scanner(System.in);
		//init user class
		UserImpl user = usesr;
		
		User logInInfo = null;
		
		System.out.println("Please enter your Username (email):");
		String email = input.nextLine();
		System.out.println("Please enter your Password:");
		String password = input.nextLine();

		if(count==0) {
			user.getAllUsers();
		}
			for(int i = 0; i<usesr.getUserList().size();i++) {
				
				if(email.equals(user.getUser(i).getEmail()) && password.equals(user.getUser(i).getPassword()) && userType.equals(user.getUser(i).getUserType())){
					System.out.println("userfound");
					logInInfo = new User(user.getUser(i).getFirstName(), user.getUser(i).getLastName(), user.getUser(i).getAge(), user.getUser(i).getEmail(),user.getUser(i).getUserType());
					break;
				}else {
					logInInfo = new User(UserType.NEW_USER);
				}
			}
	
		return logInInfo;
		
		 //return logInInfo;
	}
}

