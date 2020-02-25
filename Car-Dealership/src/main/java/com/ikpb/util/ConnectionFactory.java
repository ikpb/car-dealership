package com.ikpb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static String url;
	private static String username;
	private static String password;
	
	
	private static ConnectionFactory cf;
	public static Connection getConnection() {
		if(cf==null) {
			cf= new ConnectionFactory();
		}
		
		return cf.createConnection();
	}	
	
	
	
	
private ConnectionFactory() {
		//private constructor helps make this a singleton
		//we want to grab all of the enviornment variables 
		 url =System.getenv("POSTGRES_URL");
		 url = "jdbc:postgresql://"+url+":5000/dealership?";
		 username=System.getenv("POSTGRES_USERNAME");
		password=System.getenv("POSTGRES_PASSWORD");
		
	}

private Connection createConnection() {
	Connection conn = null;
	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e1) {
		System.out.println("Could not load PostgresSQL Driver");
		e1.printStackTrace();
	}
	try {
		conn = DriverManager.getConnection(url, username, password);
	}catch(SQLException e){
		System.out.println("Problem create DB connection");
		//in actual application we would log and not stacktrace
		e.printStackTrace();
	}
	return conn;
}
	
	
	
}
