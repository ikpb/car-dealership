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
	
	List<User> users;
	public UserImpl() {
		super();
		users = new ArrayList<User>();
		users.add(new User("Toyoda", "Matrix", "test", "123",UserType.EMPLOYEE));
		users.add(new User("Toyoda", "Matrix", "test", "123",UserType.CUSTOMER));
		users.add(new User("Toyoda", "Matrix", "bob", "123",UserType.CUSTOMER));
		users.add(new User("Honda", "Civic", "John", "123",UserType.CUSTOMER));
		getAllUsers();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String filename;
		filename = "users.dat";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			
				try {
				users = (List<User>)ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		users.add(new User(firstName, lastName,email,password,b));
		
		
	}

	@Override
	public void saveUSer(List<User> user) {
		String filename;
		filename = "users.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
			
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