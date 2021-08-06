package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;


public class QueryEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query Employees
			List<Employee> theEmployees = session.createQuery("from Employee").list(); // Employee es la java class

			// display the Employees
			displayEmployees(theEmployees);			

			// query Employee for a given company

			theEmployees = session.createQuery("from Employee s where s.company='Amazon'").list();
			System.out.println("\n\nEmployee for a given company");
			displayEmployees(theEmployees);	

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			factory.close();
		}

	}

	private static void displayEmployees(List<Employee> theEmployees) {
		for(Employee tempStudent: theEmployees) {
			System.out.println(tempStudent);
		}
	}

}
