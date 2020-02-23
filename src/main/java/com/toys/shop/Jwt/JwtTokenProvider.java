package com.toys.shop.Jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.toys.shop.Entities.Account.CustomAccountDetails;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class JwtTokenProvider {
	
	private final String JWT_SECRET = "sonhung";
	
	private final long JWT_EXPIRATION = 604800000L;
	
	public String gerenateToken(CustomAccountDetails accountDetail) {
		String jwt=null;
		Date now = new Date();
		Date expiryDate = new Date(now.getTime()+JWT_EXPIRATION);
		jwt= Jwts.builder()
					.setSubject(Integer.toString(accountDetail.getAccount().getId()))
					.setIssuedAt(now)
					.setExpiration(expiryDate)
					.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
					.compact();
		return jwt;
	}
	
	public Integer getAccountIdFromJWT(String token) {
		Claims claims=Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return Integer.parseInt(claims.getSubject());
		
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser()
			.setSigningKey(JWT_SECRET)
			.parseClaimsJws(authToken);
			
		return true;
			
		} catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
		return false;
		
	}
}
