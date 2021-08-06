package com.file.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyApp {

	public static void main(String[] args) {

		File myObj = new File("fortune.txt"); // ruta del archivo, el txt debe crearse sobre la carpeta, es decir
												// por fuera de la carpeta SRC

		// Arrays list para almacenar la cadena de strings del archivo, por salto de
		// linea
		List<String> lines = new ArrayList<String>();
		String[] arr = null;
		
		int numAleatorio;
		

		try {
			// para poder leer el archivo de texto
			Scanner myReader = new Scanner(myObj);
			// bucle para poder leer el archivo hasta que no tenga mas saltos de linea
			while (myReader.hasNextLine()) {
				// cada salto de linea lo almaceno en el arraylist
				lines.add(myReader.nextLine());
			}
			// luego cada salto de lina se guarda en un Array, donde puede ser leido y
			// utilizado
			arr = lines.toArray(new String[0]);

			// bucle para imprimir las lineas
			/*
			 * for (String var : arr) { // este tipo de for hay que estudiarlo
			 * System.out.println("var: " + var); }
			 */

			// for para imprimir campo a campo del arreglo
			/*for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}*/
			
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		numAleatorio = (int) (Math.random()*2);	

		System.out.println(arr[numAleatorio]);

	}

}
