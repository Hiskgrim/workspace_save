package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class SportConfig {

	//define bean for our sad fortune service
	
	@Bean
	public FortuneService happyFortuneService() { 
		return new HappyFortuneService();
	}
	
	//define bean for our swim coach AND inject dependency
	
	@Bean
	public Coach wrestlingCoach() {
		return new WrestlingCoach(happyFortuneService()); 
	}
	
	
}
