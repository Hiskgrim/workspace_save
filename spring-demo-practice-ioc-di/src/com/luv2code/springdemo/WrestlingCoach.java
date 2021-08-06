package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;

public class WrestlingCoach implements Coach {
	

	private FortuneService fortuneService;	
	
	
	public WrestlingCoach(FortuneService thefortuneService) {
			fortuneService = thefortuneService;
		}
	
	@Override
	public String getDailyWorkOut() {
		
		return "Eat healthy";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

}
