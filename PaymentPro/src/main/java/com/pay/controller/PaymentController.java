package com.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class PaymentController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String ORDER_SERVICE="orderService";
	
	
	@GetMapping("/output")
	@CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "orderFallBack")
	public ResponseEntity<String> getResponse() {

	    String Order_url = "http://localhost:8080/getOrder";
	    String orderFinal = restTemplate.getForObject(Order_url, String.class);

	    return ResponseEntity.ok(orderFinal + " order fetched from payment service....");
	}

	@GetMapping("/access-denied")
    public String denied() {
        return "Your email is not registered";
    }
	
	public ResponseEntity<String> orderFallBack(Exception e)
	{
		e.printStackTrace();
		System.out.println("service down called......");
		return new ResponseEntity<String>("Order service is down now...",HttpStatus.OK);
	}
	
	
	
	

}
