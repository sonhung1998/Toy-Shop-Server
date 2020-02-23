package com.toys.shop.Jwt.Payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {
	private final String accessToken;
	private String tokenType = "Bearer";
}
