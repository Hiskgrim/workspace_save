package com.luv2code.aopdemo.aspect;



import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
	
	// after alwys run no matter succes or failure of the app
	@AfterThrowing(
			pointcut="(execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..)))",
			throwing="theExc"
			)
	public void afterThrowingFindAccountAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
		
		//log the exception
		System.out.println("\n=====>>> The exception is: " + theExc);
		
	}
	
	
	
	
	//add a new advice for @AfterReturning on the findAccounts method	
	@AfterReturning(
			pointcut="(execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..)))",
			returning="result"
			)	
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		/*
		 * Toca ser consistente con el parametro que se pasa en la anotacion 
		 * comparado con el que esta en el metodo, deben ser iguales
		 */
		
		//print out which methods we are advising on
		String method = theJoinPoint.getSignature().toShortString(); // esto me da el nombre del metodo
		System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
		
		//print out the results of the method
		System.out.println("\n=====>>> result is: " + result);
		
		//let's post-process the data... let's modify it :-)
		
		
		//convert the account names to UPPERCASE
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n=====>>> result is: " + result);
		
	}
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		//loop through accounts
		
		for(Account tempAccount: result) {
			//get uppercase version of name
			String theUpperNmae = tempAccount.getName().toUpperCase();
			
			//update the name on the account
			tempAccount.setName(theUpperNmae);
		}
		
				
		
	}


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
