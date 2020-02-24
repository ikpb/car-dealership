package com.ikpb.dao;

import com.ikpb.pojo.User;

import java.util.List;
public interface UserDAO {
	List<User> getAllUsers();
	public List<User> getUserList();
	public User getUserProgram(User user);
	public User getUserDB(User user);
	public void addUser();
	public void updateUser(User user);
	public void deleteUser(User user);
	
	
}
