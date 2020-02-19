package com.ikpb.daoimpl;

import com.ikpb.dao.UserDAO;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class UserImpl implements UserDAO{
	
	ArrayList<User> users = new ArrayList<User>();

	@Override
	public List<User> getAllUsers() {
		String filename;
		filename = "users.dat";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			int num = ois.readInt();
			for(int i=0; i<num;i++) {
				try {
				Object tempObject = (Object)ois.readObject();
				User u = (User)tempObject;
				users.add(u);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return users;
	}

	@Override
	public List<User> getUserList() {
		return users;
	}

	@Override
	public void addUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your First Name: ");
		String firstName = scan.nextLine();
		System.out.println("Enter your Last Name: ");
		String lastName = scan.nextLine();
		System.out.println("Enter your Age: ");
		int age = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter your Email (This will be your Username): ");
		String email = scan.nextLine();
		System.out.println("Please enter a Password");
		String password = scan.nextLine();
		System.out.println("Are you a 1. Customer or 2. Employee (Press 1 or 2):");
		int userType = scan.nextInt();
		UserType b = UserType.NEW_USER;
		if(userType == 1) {
			b = UserType.CUSTOMER;
		}else {
			b = UserType.EMPLOYEE;
		}
		User user = new User(firstName, lastName,email,password,b);
		users.add(user);
		
	}

	@Override
	public void saveUSer() {
		String filename;
		filename = "users.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeInt(users.size());
			for(User x: users) {
				oos.writeObject(x);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public User getUser(User user) {
		User u = new User();
		for(int j=0;j<users.size();j++) {
			if(users.get(j).equals(user)) {
			 u= users.get(j);
			}
		}
		return u;
	}

	@Override
	public void deleteLastUser() {
	
			users.remove(users.size()-1);
		
		
		

}
}