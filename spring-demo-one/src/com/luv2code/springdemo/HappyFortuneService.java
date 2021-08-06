package com.luv2code.springdemo;

import java.util.Iterator;
import java.util.Random;

public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {		
		
		return "Today is your lucky day!";
	}

}
