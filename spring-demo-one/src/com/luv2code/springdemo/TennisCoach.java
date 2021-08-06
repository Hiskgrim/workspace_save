package com.luv2code.springdemo;

public class TennisCoach implements Coach {

	private FortuneService fortuneServive;
	
	public TennisCoach(FortuneService fortuneServive) {		
		this.fortuneServive = fortuneServive;
	}

	@Override
	public String getDailyWorkOut() {
		return "Practice backhand 5 hours";
	}

	@Override
	public String getDailyFortune() {		
		return null;
	}

}
