package com.sl.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sl.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query .. sort by last name
		Query<Customer> theQuery = currentSession.createQuery(
				"from Customer order by lastName", 
				Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		System.out.println(customers);
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the customer ... finally LOL
		
		if (customer.getId() == null) currentSession.save(customer);
		else currentSession.update(customer); 
		
	}

	@Override
	public Customer getCustomer(Integer theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer customer = currentSession.get(Customer.class, theId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(Integer theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
	
		theQuery.executeUpdate();
		
	}

}
