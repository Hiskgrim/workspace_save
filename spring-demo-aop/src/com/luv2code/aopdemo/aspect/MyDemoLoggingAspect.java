package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	/*
	 * Aspect: Java Class is a collection of related advices (before, after, etc.)
	 */
	
	// this is where we add all of our related advices for logging

	// let's start with an @Before advice
	
	/*
	 * IN THIS EXAMPLES I MODIFY THE RETURN TYPE ALSO THE NAME OF THE METHODS
	 * IN ACCOUNTDAO OR MEMBERSHIPDAO FOR THE EXAMPLES TO RUN
	 * 
	 */

	//@Before("execution(public void updateAccount())") //nothing match
	
	//para una clase en especifico, por eso se pone el paquete y el nombre de la clase
	//@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	
	//any method that stars with 'add' at any class
	//@Before("execution(public void add*())")
	
	//match for only type of method "void", we remove public because is not needed, its optional
	//@Before("execution(void add*())")
	
	//match any return type method that starts with add, after add the rest of the name doesnt have to match
	//@Before("execution(* add*(com.luv2code.aopdemo.Account))")
	
	//break it, not using the fully qualified name
	/*
	 * java.lang.IllegalArgumentException: warning no match for this type name: Account
	 */
	//@Before("execution(* add*(Account))")
	
	//match many arguments
	//@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
	
	//match any params
	//@Before("execution(* add*(..))")
	
	//match in our given package, any class, any method, any number of parameters
	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") 
	public void beforeAddAccountAdvice() {

		System.out.println("\n=====>>> Executing @Before advice on addAccount()");

	}

}
