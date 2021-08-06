package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class DeleteEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			int employeeId = 2;

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve Employee based on the id: primary key
			System.out.println("\nGetting Employee with id: " + employeeId);
			Employee myEmployee = session.get(Employee.class, employeeId);

			// delete Employee id=2
			System.out.println("Deleting employee id=2");
			session.createQuery("delete from Employee where id=2").executeUpdate();

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
