package com.ikpb.users;

import java.io.Serializable;

import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

public class Employee extends User implements Serializable{
	private String password;
	private String firstName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;
	
	
	@Override
	public String toString() {
		return "Employee FirstName=" + firstName + ", lastName=" + lastName + ", email=" + email;
	}
	public String setEmployeeLoginInfo() {
		return "Username: " + getEmail() + "password: " + getPassword();
	}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Employee(String firstName, String lastName, String email, String password) {
			this();
			setFirstName(firstName);
			setLastName(lastName);
			setEmail(email);
			setPassword(password);
			setUserType(UserType.EMPLOYEE);
		}
		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}

}
