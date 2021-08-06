package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD}) //Esto significa que la anotacion se puede aplicar a Metodos o Campos
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
	
	//define default course code
	public String value() default "LUV"; // ES POSIBLE PONER VARIOS VALORES POR DEFECTO, REVISAR CURSO CLASE 170
	
	//define default error message
	public String message() default "must start with LUV";
	
	//define default groups
	public Class<?>[] groups() default {}; 
	
	//define default payloads
	public Class<? extends Payload>[] payload() default {};
	
	
}
