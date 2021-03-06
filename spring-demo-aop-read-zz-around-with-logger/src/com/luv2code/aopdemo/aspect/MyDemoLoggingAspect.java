package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2) // indica el orden en que se ejecutan los aspectos
public class MyDemoLoggingAspect {

	/*
	 * Aspect: Java Class is a collection of related advices (before, after, etc.)
	 */
	
	private Logger myLogger = 
			Logger.getLogger(getClass().getName());
	
	@Around("(execution(* com.luv2code.aopdemo.service.*.getFortune(..)))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		//print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method: " + method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//now, let's execute the method
		Object result = theProceedingJoinPoint.proceed(); 
		
		//get end timestamp
		long end = System.currentTimeMillis();		
		
		//compute duration and display it
		long duration = end - begin;
		myLogger.info("\n=====>>> Duration: " + duration/1000.0 + " seconds");		
		
		return result;
	}
	

	// after like a finally
	@After("(execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..)))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @After (finally) on method: " + method);
		

	}

	// after alwys run no matter succes or failure of the app
	@AfterThrowing(pointcut = "(execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..)))", throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		myLogger.info("\n=====>>> The exception is: " + theExc);

	}

	// add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(pointcut = "(execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..)))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

		/*
		 * Toca ser consistente con el parametro que se pasa en la anotacion comparado
		 * con el que esta en el metodo, deben ser iguales
		 */

		// print out which methods we are advising on
		String method = theJoinPoint.getSignature().toShortString(); // esto me da el nombre del metodo
		myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);

		// print out the results of the method
		myLogger.info("\n=====>>> result is: " + result);

		// let's post-process the data... let's modify it :-)

		// convert the account names to UPPERCASE
		convertAccountNamesToUpperCase(result);

		myLogger.info("\n=====>>> result is: " + result);

	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		// loop through accounts

		for (Account tempAccount : result) {
			// get uppercase version of name
			String theUpperNmae = tempAccount.getName().toUpperCase();

			// update the name on the account
			tempAccount.setName(theUpperNmae);
		}

	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		myLogger.info("\n=====>>> Executing @Before advice on addAccount()");

		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		myLogger.info("Method: " + methodSig);

		// display method arguments

		// get args
		Object[] args = theJoinPoint.getArgs(); // array of objects, in this case the args of the method

		// loop thru args
		for (Object tempArg : args) {
			myLogger.info(tempArg.toString());

			if (tempArg instanceof Account) {

				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;

				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account level: " + theAccount.getLevel());
			}
		}

	}

}
