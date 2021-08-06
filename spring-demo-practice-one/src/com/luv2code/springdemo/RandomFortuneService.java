package com.luv2code.springdemo;

public class RandomFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		String[] fortune = {"Buen dia", "Mal dia", "Dia Regular"};		
		int numAleatorio;
		
		numAleatorio = (int) (Math.random()*2);		
		System.out.println(numAleatorio);		
		return fortune[numAleatorio];
	}

}
