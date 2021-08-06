package com.luv2code.springdemo;

public class GolfCoach implements Coach {

	private FortuneService fortuneService;	
	
	
	public GolfCoach(FortuneService theFortuneService) {
		this.fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkOut() {		
		return "Daily Stuff practice";
	}

	@Override
	public String getDailyFortune() {		
		return fortuneService.getFortune();
	}

}
