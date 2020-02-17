package com.ikpb.users;

import java.io.Serializable;

import com.ikpb.pojo.User;
import com.ikpb.pojo.User.UserType;

public class Employee extends User implements Serializable{
	private String password;
	@Override
	public String toString() {
		return "Employee [getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getAge()="
				+ getAge() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getUserType()="
				+ getUserType() + "]" + "\r\n";
	}
	public String setEmployeeLoginInfo() {
		return "Username: " + getEmail() + "password: " + getPassword();
	}
		//view payments
		private int viewAllPayments() {
			return 0;
		}
		//view cars on a lot
		public String viewCarsOnLot() {
			return null;
		}
		//make an offer on a care
		public int addCarToLot() {
			return 0;
		}
		public boolean removeCarFromLot() {
			return false;
		}
		
		

		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Employee(String firstName, String lastName, int age, String email, String password, UserType user) {
			this();
			setFirstName(firstName);
			setLastName(lastName);
			setAge(age);
			setEmail(email);
			setPassword(password);
			setUserType(user);
			
			
		}
		public Employee(String email, String pasword)
		{
			this();
			setEmail(email);
			setPassword(password);
		}
		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Employee(String firstName, String lastName, int age, String email,
				UserType UserClassification) {
	
		}
}
