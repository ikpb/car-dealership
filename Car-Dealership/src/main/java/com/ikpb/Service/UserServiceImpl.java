package com.ikpb.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ikpb.dao.UserDAO;
import com.ikpb.daoimpl.UserImpl;
import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;
import com.ikpb.util.ConnectionFactory;

public class UserServiceImpl implements UserService {
	private static UserDAO uDao = new UserImpl();
	@Override
	public void updatePassword(String email, String password) {
		//uDao.updateUser();
	}

	@Override
	public User loginUser(String email, String password) {
		User u = uDao.getUserDB(email);
		if(u!=null && u.getPassword().contentEquals(password)) {
			return u;
		}
		return null;
	}

	@Override
	public void createUserGarage(String email, Car car) {
		try{
//			Car tempCar;
			Connection conn =  ConnectionFactory.getConnection();
			//putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("insert into payment(userid,totalleft,paymentamount,carid) values (?,?,?,?)");
			ps.setString(1, email);
			ps.setDouble(2, car.getCost());
			ps.setDouble(3, car.getPayment());
			ps.setString(4, car.getVin());
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a Resultset
//			while(rs.next()) {
//				
//				tempCar  = new Car(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getInt("year"), rs.getDouble("price"),rs.getBoolean("isavaliable"));
//			System.out.println(tempCar.toString());	
//			}
			ps.execute();
			//allows us to execute a query without a result
			conn.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
