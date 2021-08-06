package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;



public class ReadEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			// create a employee object
			System.out.println("Creating new student object...");
			Employee tempEmployee = new Employee("Daffy", "Duck", "Servientrega INC");

			// start a transaction
			session.beginTransaction();

			// save the employee object
			System.out.println("Saving the Employee...");
			System.out.println(tempEmployee);
			session.save(tempEmployee);

			// commit transaction
			session.getTransaction().commit();

			// MY NEW CODE

			// find out the Employee's id: primary key
			System.out.println("Saved Employee. Generated id: " + tempEmployee.getId());

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve Employee based on the id: primary key
			System.out.println("\nGetting Employee with id: " + tempEmployee.getId());

			Employee myEmployee = session.get(Employee.class, tempEmployee.getId());

			System.out.println("Get complete: " + myEmployee);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			factory.close();
		}

	}

}
