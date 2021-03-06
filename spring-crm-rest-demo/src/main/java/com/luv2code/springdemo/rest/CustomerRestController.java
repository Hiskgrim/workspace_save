package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;

	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerService.getCustomers();

	}

	// add mapping for GET /customers/{customerId}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		return theCustomer;
	}

	// add mapping for POST /customers - add new customer

	/*
	 * in the background Jackson con esta funcion @RequestBody
	 * 
	 * toma el JSON y lo convierte en POJO, para que lo podamos usar normalmente
	 * 
	 * con Hibernate ETC.
	 * 
	 * 
	 */

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update

		/*
		 * Hibernate funciona asi:
		 * 
		 * Si PK empty --> EMPTY = 0, para set 0 el PK INSERT sino UPDATE
		 * 
		 * ademas si se llegara a enviar el id en la peticion de POST
		 * 
		 * se pone a 0 para que se asigne automaticamente
		 * 
		 * esto quiere decir que el en el JSON en el POST no se debe por el id
		 * 
		 * 
		 * algo asi:
		 * 
		 * { "id": 4, "firstName": "Mary", "lastName": "Public", "email":
		 * "mary@luv2code.com" }
		 * 
		 * 
		 */

		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	// add mapping for PUT /customers - update existing customer

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		/*
		 * como el ID existe o esta set
		 * entonces hibernate lo interpreta como un update
		 * 
		 */
		
		customerService.saveCustomer(theCustomer);

		return theCustomer;

	}

	// add mapping for DELETE /customers/{customerId} - delete customer

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		// i just retrieve the id from the DAO to see if is not NULL		
		// is == null then trhwo exception else proceed
		//because we have an id to delete in the Database
		
		Customer tempCustomer = customerService.getCustomer(customerId);

		// throw exception if null

		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		customerService.deleteCustomer(customerId);

		return "Deleted customer id - " + customerId;
	}

}
