package com.file.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlyArrayListApp {

	public static void main(String[] args) {

		File myObj = new File("fortune.txt");
		List<String> lines = new ArrayList<String>();
		int numAleatorio;
		
		
		try {
			// para poder leer el archivo de texto
			Scanner myReader = new Scanner(myObj);
			// bucle para poder leer el archivo hasta que no tenga mas saltos de linea
			while (myReader.hasNextLine()) {
				// cada salto de linea lo almaceno en el arraylist
				lines.add(myReader.nextLine());
			}
			
					
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		numAleatorio = (int) (Math.random()*2);	

		System.out.println(lines.get(numAleatorio));

	}

}
