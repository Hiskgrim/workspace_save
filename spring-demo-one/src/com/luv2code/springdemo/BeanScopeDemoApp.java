package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		//load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		
		//retrieve bean from spring container
		
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		//check if they are the same
		boolean result = (theCoach == alphaCoach);
		
		//print out results
		System.out.println("\nPointing to the same object: " + result);
		
		System.out.println("\nMemory location for theCoach: " + theCoach); // esta apuntando a la misma parte de la memoria 52719fb6, cuando es un singleton
		
		System.out.println("\nMemory location for alphaCoach: " + alphaCoach); // 52719fb6
		
		//close context
		
		context.close();
		
		/*
		 * Cuando es prototype: 
		 * 
		 * 		Pointing to the same object: false

				Memory location for theCoach: com.luv2code.springdemo.TrackCoach@3012646b

				Memory location for alphaCoach: com.luv2code.springdemo.TrackCoach@4a883b15
		 * 
		 * 
		 */
		

	}

}
