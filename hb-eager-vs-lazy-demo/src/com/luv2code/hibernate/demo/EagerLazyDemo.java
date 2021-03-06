package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
					
			//get instructor from db
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println("luv2code: Instructor: " + tempInstructor);
			
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
							
			//commit transaction
			session.getTransaction().commit();
			
			//close the sesion
			session.close();
			
			//since this is lazy data... this should fail, session closed
			
			//HOW TO RESOLVE ERROR
			
			System.out.println("\nluv2code: The session is now closed!!\n");
			
			//Option 1: call getter method while session is open
			
			//get course for the instrcutor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses()); //lazy data
			
			System.out.println("luv2code: Done!!");
			
		} 
		
		//run wiht out cath to see error  org.hibernate.LazyInitializationException
		
		/*catch (Exception e) {
			e.getStackTrace();
		}*/
		
		
		finally {
			//session.close();
			factory.close();
		}
		
		
		

	}

}
