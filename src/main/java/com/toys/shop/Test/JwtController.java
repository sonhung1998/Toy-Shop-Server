package com.toys.shop.Test;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Entities.Account.CustomAccountDetails;
import com.toys.shop.Jwt.JwtTokenProvider;
import com.toys.shop.Jwt.Payload.LoginRequest;
import com.toys.shop.Jwt.Payload.LoginResponse;
import com.toys.shop.Jwt.Payload.RandomStuff;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class JwtController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtProvider;

	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		log.info("ACCOUNT:"+loginRequest.toString());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		String jwt = null;
		try {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			jwt = jwtProvider.gerenateToken((CustomAccountDetails) authentication.getPrincipal());
			log.info("JWT:" + jwt);
		} catch (Exception e) {
			log.error("Lỗi xảy ra:" + e.getMessage());
		}
		return new LoginResponse(jwt);
	}

	@GetMapping("/random")
	public RandomStuff randomStuff() {
		return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
	}

	@GetMapping("/test")
	public String test() {

		return "test";
	}

}
