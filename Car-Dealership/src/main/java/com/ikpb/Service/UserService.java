package com.ikpb.Service;

import com.ikpb.pojo.Car;
import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

public interface UserService {
	public void updatePassword(String username, String password);
	public User loginUser(String username, String password, UserType usertype);
	public void createUserGarage(String email, Car car);
}
