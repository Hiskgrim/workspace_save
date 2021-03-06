package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
				
		try {
						
			// start a transaction
			session.beginTransaction();
					
			//get the student mary from database
			int studentId = 2;			
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded Student: "+tempStudent);
			System.out.println("\nCourse: "+tempStudent.getCourses());
			
			//create more courses
			Course tempCourse1 = new Course("Rubik's Cube - How to speed Cube");
			Course tempCourse2 = new Course("Atari 2600 - Game Development");
			
			//add mary to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			//save courses
			System.out.println("\nSaving the courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} catch (Exception e) {
				e.getStackTrace();
		}finally {
			session.close();
			factory.close();
		}
		
		
		

	}

}
