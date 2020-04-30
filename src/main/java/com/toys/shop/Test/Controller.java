package com.toys.shop.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping(value = { "/", "/home" })
	public String home() {
		return "home";
	}

	@GetMapping(value = { "/success" })
	public String success() {
		return "login success";
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/random")
	public String admin() {
		return "Random";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "You need admin role to access this page !";
	}
	

}
