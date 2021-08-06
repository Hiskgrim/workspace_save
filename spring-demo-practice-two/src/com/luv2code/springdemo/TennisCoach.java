package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("thatSillyCoach") //Explicit beanId, le damos un id al bean
@Component //default id bean, nombre de la clase, con la primer letra en minusculas tennisCoach
public class TennisCoach implements Coach {

	@Autowired
	private FortuneService fortuneService;
	
	public TennisCoach() {
		System.out.println("TennisCoach: Inside no-arg Constructor-default");
	}
	
	@Override
	public String getDailyWorkOut() {		
		return "Practice your backhand volley - Tennis";
	}

	@Override
	public String getDailyFortune() {		
		return fortuneService.getFortune();
	}

}
