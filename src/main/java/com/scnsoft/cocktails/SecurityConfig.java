package com.scnsoft.cocktails;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.scnsoft.cocktails.dao.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(login -> userRepository.findByLogin(login)
        		.orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User: %s, not found", login)
                    )
                ))
        	.passwordEncoder(passwordEncoder());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http = http
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and();
		
		http
        .authorizeRequests()
          .antMatchers("/api/auth").permitAll()
          .anyRequest().authenticated()
          .and()
          .logout().logoutUrl("/api/logout").logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))).permitAll()
          .and().cors().disable().csrf().disable();
		
		http.addFilterBefore(
	            jwtTokenFilter,
	            UsernamePasswordAuthenticationFilter.class
	        );
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
		
		String idForEncode = "bcrypt";
		Map encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		encoders.put(null, NoOpPasswordEncoder.getInstance());
		
		return new DelegatingPasswordEncoder(idForEncode, encoders);
    }
	
//	@Bean
//	public JdbcUserDetailsManager jdbcUserDetailsManager()
//	{
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//		jdbcUserDetailsManager.setDataSource(dataSource);
//		
//		jdbcUserDetailsManager.setUserExistsSql("select login from user where login = ?");
//		
//		return jdbcUserDetailsManager;
//	}
	
//	@Override 
//	@Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

}
