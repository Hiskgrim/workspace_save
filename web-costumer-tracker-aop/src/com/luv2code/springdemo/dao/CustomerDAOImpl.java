package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	// @Transactional //now implements from de CustomerService
	public List<Customer> getCustomers(int theSortField) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// determine sort field
		String theFieldName = null;

		switch (theSortField) {
		case SortUtils.FIRST_NAME:
			theFieldName = "firstName";
			break;
		case SortUtils.LAST_NAME:
			theFieldName = "lastName";
			break;
		case SortUtils.EMAIL:
			theFieldName = "email";
			break;
		default:
			// if nothing matches the default to sort by lastName
			theFieldName = "lastName";
		}

		// create a query
		String queryString = "from Customer order by " + theFieldName;
		Query<Customer> theQuery = currentSession.createQuery(queryString, Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;

		/*
		 * OLD RETRIEVE DATA WITHOUT SORTING
		 * 
		 * // get the current hibernate session Session currentSession =
		 * sessionFactory.getCurrentSession();
		 * 
		 * // create a query ... sort by last name Query<Customer> theQuery =
		 * currentSession.createQuery("from Customer order by lastName",
		 * Customer.class);
		 * 
		 * // execute query and get result list List<Customer> customers =
		 * theQuery.getResultList();
		 * 
		 * // return the results return customers;
		 */
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the customer...finally LOL
		currentSession.saveOrUpdate(theCustomer);

		// saveOrUpdate
		/*
		 * If PK is empty then INSERT new customer else UPDATE existing customer
		 */

	}

	// prepopulate fields on add customer
	@Override
	public Customer getCustomer(int theId) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary KEY ...finally LOL
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:customerId"); // customerId
																											// aca y
																											// abajo
																											// deben ser
																											// la misma
																											// variable
		theQuery.setParameter("customerId", theId);

		theQuery.executeUpdate(); // simplemente ejecuta lo que tenga la consulta HQL.

	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;
	}

}
