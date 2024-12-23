package vn.iotstar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.models.Customer;

@RestController
@EnableMethodSecurity
public class CustomerController {

	final private List<Customer> customers = List.of(
			Customer.builder().id("001").name("Luong Sy Nam").email("luongsynam1004@gmail.com").build(),
			Customer.builder().id("002").name("Sy Nam").email("22110185@student.hcmute.edu.vn").build()
			);
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
	    return ResponseEntity.ok("hello is Guest");
	}

	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList() {
	    List<Customer> list = this.customers;
	    return ResponseEntity.ok(list);
	}

	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
	    List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
	    return ResponseEntity.ok(customers.get(0));
	}
}
