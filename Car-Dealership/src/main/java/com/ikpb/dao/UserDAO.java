package com.ikpb.dao;

import com.ikpb.pojo.User;

import java.util.List;
public interface UserDAO {
	List<User> getAllUsers();
	public List<User> getUserList();
	public User getUser(User user);
	public void addUser();
	public void saveUSer(List<User> user);
	public void deleteLastUser();
	
	
}
