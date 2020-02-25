package com.ikpb.daoimpl;

import com.ikpb.dao.UserDAO;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;
import com.ikpb.util.ConnectionFactory;

import java.util.logging.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
public class UserImpl extends User implements UserDAO {
	private static final Logger logger = Logger.getLogger(UserImpl.class);
//	private static String url ="jdbc:postgresql://localhost:5000/dealership";
	//jdbc:postgresql://host:port/database_name
//	private static String username="postgres";
//	private static String password="root";
	List<User> users = new ArrayList<User>();
//	public UserImpl() {
//		super();
//		users ;
//		users.add(new User("toyoda", "Matrix", "test", "123",UserType.EMPLOYEE));
//		users.add(new User("johny", "twochains", "john", "123",UserType.CUSTOMER));
//		users.add(new User("bri", "Davis", "bob", "123",UserType.CUSTOMER));
//		users.add(new User("mark", "parez", "mark", "123",UserType.CUSTOMER));
//		saveUSer(users);
//		getAllUsers();
//	}
	
//// retrieves all of the users from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List <User> tempListUsers = new ArrayList<User>();
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM appuser");
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			while(rs.next()) {
				tempListUsers.add(new User(rs.getString("firstname"), rs.getString("lastname"),rs.getString("email"),rs.getString("password"), (UserType)rs.getObject("usertype")));
			}
			
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return tempListUsers;
	}

	
	////returns the program side of the userlist
	////////////////////
	@Override
	public List<User> getUserList() {
		return users;
	}

	
	////adds user to the data base
	///////////////////
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
		String passwords = scan.nextLine();
		System.out.println("Are you a 1. Customer or 2. Employee (Press 1 or 2):");
		int userType = scan.nextInt();
		UserType b = UserType.NEW_USER;
		if(userType == 1) {
			b = UserType.CUSTOMER;
		}else {
			b = UserType.EMPLOYEE;
		}
		//while adding the user to the database we will also add the list
		users.add(new User(firstName, lastName,email,passwords,b));
		try{
			Connection conn = ConnectionFactory.getConnection();
			//puttingn in a native sql query utilizing a perpared statemtn
			PreparedStatement ps = conn.prepareStatement("Insert INTO appuser VALUES(?,?,?,?,?)");
			ps.setString(1,firstName);
			//seting the first question mark to be the name that is passed as
			//paramenter, that belongs to our user object
			ps.setString(2,lastName);
			//setting the second question mark to be the type that belongs
			//to our user object
			ps.setString(3,email);
			//seting the third question mark to be the name that is passed as
			//paramenter, that belongs to our user object
			ps.setString(4,passwords);
			//setting the fouth question mark to be the type that belongs
			//to our user object
			ps.setString(5, b.toString());
			//setting the fifth question mark to be the type that belongs
			//to our user object
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
///save the current list of users back to the database/ update users in the database
	@Override
	public void updateUser(User u) {
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("UPDATE appuser SET firstname=?, lastname=?,password=?, usertype=? WHERE email=?");
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getPassword());
			ps.setObject(4, u.getUserType().toString());
			ps.setObject(5, u.getEmail());
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
	
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
////////// get a user from the program list.
	////////////////////
	@Override
	public User getUserProgram(User user) {
		User u = new User();
		for(int j=0;j<users.size();j++) {
			if(users.get(j).equals(user)) {
			 u= users.get(j);
			}
		}
		return u;
	}
	///////retrieve a user from the database
	///////////////////
	public User getUserDB(String email) {
		User tempUser = null;
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a perpared statemnt
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM appuser WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
			while(rs.next()) {
				tempUser = new User(rs.getString("firstname"), rs.getString("lastname"),rs.getString("email"),rs.getString("password"), User.UserType.valueOf(rs.getString("usertype")));
			}
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
	
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return tempUser;
	}
	////update a users info without sending into to the database/soft update.
	/////////////////
	public void updateUserProgram(User user) {
		System.out.println(user);
		System.out.println(user.getCarList()+"inside userimpl");
		for(int i=0; i<users.size();i++) {
			if(users.get(i).getEmail().contentEquals(user.getEmail())) {
				String tempString = users.get(i).getPassword();
				users.set(i, user);
				users.get(i).setPassword(tempString);
				users.get(i).getCarList();
				System.out.println(users.get(i).getCarList() + "inside userimpl after change over");
			}
		}
	}

	@Override
	public void deleteUser(User user) {
		try{
			Connection conn = ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("DELETE FROM appuser WHERE email=?");
			ps.setString(1, user.getEmail());
			ps.executeUpdate();
			//allows us to execute a query without a result
			conn.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}