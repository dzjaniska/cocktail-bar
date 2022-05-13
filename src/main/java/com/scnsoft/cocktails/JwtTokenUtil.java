package com.scnsoft.cocktails;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil {

	public boolean valid(String token) {
		
		try {
			Jwts.parserBuilder().build().parse(token);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	public UUID getUserId(String token) {
		
		Claims body = Jwts.parserBuilder()
				.build()
                .parseClaimsJwt(token)
                .getBody();
		
		return UUID.fromString(body.get("userId", String.class));
	}

	public String generateToken(User u) {
        Claims claims = Jwts.claims();
        claims.put("userId", u.getId().toString());
        claims.put("role", u.getRole().toString());

        return Jwts.builder()
                .setClaims(claims)
                .compact();
    }
}
