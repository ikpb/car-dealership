package com.king.pojo;

public class User {
	public enum UserType{
		CUSTOMER, EMPLOYEE, NEW_USER;
	}
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserType usertype;
	private int offer;
	private int makePayment;
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", usertype=" + usertype + ", offer=" + offer + ", makePayment=" + makePayment + "]";
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
	public UserType getUsertype() {
		return usertype;
	}
	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}
	public int getMakePayment() {
		return makePayment;
	}
	public void setMakePayment(int makePayment) {
		this.makePayment = makePayment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + makePayment;
		result = prime * result + offer;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((usertype == null) ? 0 : usertype.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
		if (makePayment != other.makePayment)
			return false;
		if (offer != other.offer)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (usertype != other.usertype)
			return false;
		return true;
	}
	public User(String firstName, String lastName, String email, String password, UserType usertype, int offer,
			int makePayment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
		this.offer = offer;
		this.makePayment = makePayment;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
