package com.sl.springdemo.service;

import java.util.List;

import com.sl.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(Integer theId);

	public void deleteCustomer(Integer theId);

}
