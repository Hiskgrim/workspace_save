package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

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
			
			//option 2: Hibernate query with HQL
			
			
			
			
			//get instructor from db
			int theId = 1;
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id=:theInstructorId", 
							Instructor.class);
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			//exeucte the query and get the instrcutor
			Instructor tempInstructor = query.getSingleResult();
			System.out.println("luv2code: Instructor: " + tempInstructor);
			
			
							
			//commit transaction
			session.getTransaction().commit();
			
			//close the sesion
			session.close();		
			
			System.out.println("\nluv2code: The session is now closed!!\n");		
			
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
