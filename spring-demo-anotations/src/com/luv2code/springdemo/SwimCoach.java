package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Value;

public class SwimCoach implements Coach {

	private FortuneService fortuneService;
	
	@Value("${foo.email}")
	private String Email;
	
	@Value("${foo.team}")
	private String team;
	
	public SwimCoach(FortuneService thefortuneService) {
		fortuneService = thefortuneService;
	}
	
	
	@Override
	public String getDailyWorkOut() {		
		return "Swim 10 meters as a warm up.";
	}

	@Override
	public String getDailyFortune() {		
		return fortuneService.getFortune();
	}


	public String getEmail() {
		return Email;
	}


	public String getTeam() {
		return team;
	}

	
	
	
}
