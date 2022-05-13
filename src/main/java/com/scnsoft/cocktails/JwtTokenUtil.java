package com.scnsoft.cocktails;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil {

	public boolean valid(String token) {
		
		// TODO Auto-generated method stub
		try {
			Jwts.parserBuilder().build().parse(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}

	public UUID getUserId(String token) {
		
		// TODO Auto-generated method stub
		Claims body = Jwts.parserBuilder()
				.build()
                .parseClaimsJwt(token)
                .getBody();
		
		return body.get("userId", UUID.class);
	}

	public String generateToken(User u) {
        Claims claims = Jwts.claims();
        claims.put("userId", u.getId());
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .compact();
    }
}
