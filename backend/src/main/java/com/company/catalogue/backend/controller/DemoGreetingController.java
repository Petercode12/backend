package com.company.catalogue.backend.controller;


import com.company.catalogue.backend.data.DemoGreeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
public class DemoGreetingController {

	//demo hello with id and username
	private static final String greetingTemplate = "Hello World, %s %s";
	private final AtomicLong counter = new AtomicLong();
	// Value of id should be increased 1 when user request one time
	@GetMapping("/greeting")
	public DemoGreeting greeting(@RequestParam(value = "gender", defaultValue = "0") boolean gender,
								 @RequestParam(value = "userName", defaultValue = "user") String userName) {
		return DemoGreeting.builder()
				.id(counter.incrementAndGet())
				.content(String.format(greetingTemplate, gender ? "Mr. " : "Mrs. ", userName))
				.build();

	}
}
