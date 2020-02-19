package com.ikpb.dao;

import java.util.List;

import com.ikpb.pojo.Customer;

public interface CustomerDAO {
List<Customer> getAllCustomers();
Customer getCustomerByEmail(String email);
void saveCustomer(Customer cust);
}
