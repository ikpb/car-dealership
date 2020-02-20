package com.ikpb.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.ikpb.dao.CustomerDAO;
import com.ikpb.pojo.Customer;
import com.ikpb.pojo.User;

public class CustomerImpl implements CustomerDAO{

	private List<Customer> cust;
	public CustomerImpl() {
		cust = new ArrayList<>();
		cust.add(new Customer("Bob", "Soyer", "asdf"));
	}
	public List<Customer> getAllCustomers() {
		
		return cust;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		int custIndex = 0;
		for(int i =0; i<cust.size();i++) {
			if(cust.get(i).getEmail().equalsIgnoreCase(email))
				{ custIndex = i;}
		
		}return cust.get(custIndex);
	}
	public void addCustomer(User user) {
		cust.add((Customer)user);
	}
	

	@Override
	public void saveCustomer(Customer custs) {
		cust.add(custs);
		
	}

}
