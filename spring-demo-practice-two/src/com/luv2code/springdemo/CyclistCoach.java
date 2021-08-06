package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CyclistCoach implements Coach {
	
	@Autowired
	private FortuneService fortuneService;

	public CyclistCoach() {
		System.out.println("CyclistCoach: Inside default constructor");
	}
	
	@Override
	public String getDailyWorkOut() {		
		return "Practice cardio 1 hours - Cyclist";
	}

	@Override
	public String getDailyFortune() {		
		return fortuneService.getFortune();
	}

}
