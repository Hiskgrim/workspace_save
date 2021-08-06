package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

//@Component("thatSillyCoach") //Explicit beanId, le damos un id al bean
@Component //default id bean, nombre de la clase, con la primer letra en minusculas tennisCoach
//@Scope("prototype") // anotacion para espeficiar el 
					//scope o modo de creacion de los beans, en este caso cambia de singleton el default a protorype
public class TennisCoach implements Coach {

	// field injection, no need or contructor injection or setter injection method
	@Autowired
	//@Qualifier("happyFortuneService") // sirve para especificar que bean usar cuando existen mas de uno, en este caso hay 4
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
				
	// define constructor to the injection
	/*@Autowired
	public TennisCoach(FortuneService thefortuneService) {
		this.fortuneService = thefortuneService;
	}*/
	
	// define dedault constructor
	//cuando el scope es diferente, por ejemplo, prototype se crearion dos instancias del constructor
	public TennisCoach() {
		System.out.println(">>TennisCoach: Inside default constructor");
	}
	
	//define a setter method
	/*@Autowired
	public void setFortuneService(FortuneService thefortuneService) {
		System.out.println(">>TennisCoach: Inside default setFortuneService() method");
		fortuneService = thefortuneService;
	}*/
	
	// para tener la inyeccion se puede hacer no solo con un constructor o un metodo set, si no
	//con cualquier metodo
	/*@Autowired
	public void doSomeCrazyStuff(FortuneService thefortuneService) {
		System.out.println(">>TennisCoach: Inside default doSomeCrazyStuff() method");
		fortuneService = thefortuneService;
	}*/

	
	//define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> TennisCoach: Inside of doMyStartupStuff");
	}	
	
	//define my destroy method
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println(">> TennisCoach: Inside of doMyCleanupStuff");
	}
	
	@Override
	public String getDailyWorkOut() {		
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {		
		return fortuneService.getFortune();
	}

}
