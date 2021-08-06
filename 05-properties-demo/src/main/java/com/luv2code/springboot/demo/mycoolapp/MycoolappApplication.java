package com.luv2code.springboot.demo.mycoolapp;


// SpringApplication --> for BootsTrap your spring app
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 
 * @SpringBootApplication -> package of annotations
 * 
 * ex;
 *  @EnableAutoConfiguration
 *  @ComponentScan
 *  @Configuration
 * 
 */

@SpringBootApplication
public class MycoolappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycoolappApplication.class, args);
	}

}
