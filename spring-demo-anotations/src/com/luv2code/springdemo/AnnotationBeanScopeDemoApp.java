package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {

		// load spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// retrieve bean from spring container

		Coach theCoach = context.getBean("tennisCoach", Coach.class);

		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);

		// check if they are the same
		boolean result = (theCoach == alphaCoach);

		// print out results
		System.out.println("\nPointing to the same object: " + result);

		// esta apuntando a la misma parte de la
		// memoria 52719fb6, cuando es un singleton
		System.out.println("\nMemory location for theCoach: " + theCoach);

		System.out.println("\nMemory location for alphaCoach: " + alphaCoach); // 52719fb6

		// close context
		context.close();

	}

}
