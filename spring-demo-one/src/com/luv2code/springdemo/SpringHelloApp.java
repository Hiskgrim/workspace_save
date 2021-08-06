package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloApp {

	public static void main(String[] args) {
		
		
		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext ("applicationContext.xml");
				
		//retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);  //has depedencies
				
		//call methods on the bean
		System.out.println(theCoach.getDailyWorkOut());
		
		System.out.println(theCoach.getDailyFortune());
		
		//close context
		context.close();

	}

}
