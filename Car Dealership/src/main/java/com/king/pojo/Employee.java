package com.king.pojo;

public class Employee extends User{
	public enum UserType{
		CUSTOMER, EMPLOYEE, NEW_USER;
	}
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserType userType;
	String acceptOffer = null;
	@Override
	public String toString() {
		
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", userType=" + userType + ", acceptOffer=" + acceptOffer + "]";
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public boolean isAcceptOffer() {
		return acceptOffer;
	}
	public void setAcceptOffer(boolean acceptOffer) {
		this.acceptOffer = acceptOffer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (acceptOffer ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (acceptOffer != other.acceptOffer)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}
	public Employee(String firstName, String lastName, String email, String password,
			com.king.pojo.User.UserType usertype, int offer, int makePayment, String firstName2, String lastName2,
			String email2, String password2, UserType userType2, boolean acceptOffer) {
		super(firstName, lastName, email, password, usertype, offer, makePayment);
		firstName = firstName2;
		lastName = lastName2;
		email = email2;
		password = password2;
		userType = userType2;
		this.acceptOffer = acceptOffer;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String firstName, String lastName, String email, String password,
			com.king.pojo.User.UserType usertype, int offer, int makePayment) {
		super(firstName, lastName, email, password, usertype, offer, makePayment);
		// TODO Auto-generated constructor stub
	}
	
}
