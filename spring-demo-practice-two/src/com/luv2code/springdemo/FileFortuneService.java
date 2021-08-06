package com.luv2code.springdemo;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class FileFortuneService implements FortuneService {
	
	private File myObj = new File("fortune.txt");
	private List<String> lines = new ArrayList<String>();	
	private int numAleatorio;
	
	@Override
	public String getFortune() {		
		
		try {
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				// cada salto de linea lo almaceno en el arraylist
				lines.add(myReader.nextLine());
			}
				
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		numAleatorio = (int) (Math.random()*(lines.size()));	
		return lines.get(numAleatorio); //Tomamos un numero aleatorio y obtenemos el registro del arraylist

	}

}
