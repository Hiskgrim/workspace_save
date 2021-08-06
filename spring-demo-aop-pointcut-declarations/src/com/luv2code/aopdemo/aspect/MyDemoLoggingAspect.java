package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	/*
	 * Aspect: Java Class is a collection of related advices (before, after, etc.)
	 */
	
	// this is where we add all of our related advices for logging

	// let's start with an @Before advice
		
	// con esto empaquetamos el advice y lo pasamos como paramtro en el beofre
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	/*
	 * los siguientes before se van a ejecutar antes del pointcut, si son llamadas
	 * en el main app. 
	 */
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {

		System.out.println("\n=====>>> Executing @Before advice on addAccount()");

	}

	
	@Before("forDaoPackage()")
	public void perfomApiAnalytics() {

		System.out.println("\n=====>>> Performing API analytics");

	}

	
}
