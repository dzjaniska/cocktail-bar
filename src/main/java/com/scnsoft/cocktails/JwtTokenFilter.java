package com.scnsoft.cocktails;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.entity.User;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.equals("") || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        
        String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.valid(token)) {
            chain.doFilter(request, response);
            return;
        }
        
        User user = userRepository
                .findById(jwtTokenUtil.getUserId(token))
                .orElse(null);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, null,
            user == null ?
                List.of() : user.getAuthorities()
        );
        
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
            );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}

}
