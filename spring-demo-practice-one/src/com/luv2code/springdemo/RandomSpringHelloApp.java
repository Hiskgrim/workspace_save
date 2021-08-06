package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RandomSpringHelloApp {

	public static void main(String[] args) {
		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");

		// retrieve bean from spring container
		Coach theCoach = context.getBean("myGolfCoach", Coach.class); // has depedencies

		Coach alphaCoach = context.getBean("myGolfCoach", Coach.class);

		// check if they are the same
		boolean result = (theCoach == alphaCoach);

		// print out results
		System.out.println("\nPointing to the same object: " + result);

		System.out.println("\nMemory location for theCoach: " + theCoach); // esta apuntando a la misma parte de la
																			// memoria 52719fb6, cuando es un singleton

		System.out.println("\nMemory location for alphaCoach: " + alphaCoach);

		// call methods on the bean
		System.out.println(theCoach.getDailyWorkOut());

		System.out.println(theCoach.getDailyFortune());

		// close context
		context.close();

	}

}
