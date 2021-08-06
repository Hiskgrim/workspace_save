package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		//read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean from spring container
		//Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
		Coach theCoach = context.getBean("tennisCoach", Coach.class); //default id bean
		
		//call a method on the bean
		System.out.println(theCoach.getDailyWorkOut());
		
		//call a method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		//close context
		context.close();
		
	}

}
