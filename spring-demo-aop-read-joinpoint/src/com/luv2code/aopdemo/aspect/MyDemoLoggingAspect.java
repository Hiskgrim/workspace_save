package com.luv2code.aopdemo.aspect;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2) //indica el orden en que se ejecutan los aspectos
public class MyDemoLoggingAspect {

	/*
	 * Aspect: Java Class is a collection of related advices (before, after, etc.)
	 */
	
	// this is where we add all of our related advices for logging

	// let's start with an @Before advice
		
	// con esto empaquetamos el advice y lo pasamos como paramtro en el beofre
	//any method any param
	
	/*
	 * WE JUST MOVE THE EXPRESIONS TO A NEW CLASS TO SHARE WITH ALL OTHER NEW AND
	 * OLD CLASES
	 * 
	 */
	
	/*@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() { }
	
	//create pointcut for getter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getter() {	}
	
	//create pointcut for setter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setter() {	}
	
	//create pointcut: include package....exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() { }*/
	
	
	/*
	 * los siguientes before se van a ejecutar antes del pointcut, si son llamadas
	 * en el main app. 
	 */
	
	/*
	 * JoinPoint will give us information about the method we are executing
	 *  
	 */
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		System.out.println("\n=====>>> Executing @Before advice on addAccount()");
		
		//display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method: "+ methodSig);
				
		//display method arguments
		
		
		//get args
		Object[] args = theJoinPoint.getArgs(); //array of objects, in this case the args of the method
		
		//loop thru args
		for(Object tempArg: args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				
				//downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("account name: "+ theAccount.getName() );
				System.out.println("account level: "+ theAccount.getLevel() );
			}
		}
		
	}


	
}
