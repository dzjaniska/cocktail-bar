package com.scnsoft.cocktails;

import com.scnsoft.cocktails.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserRepository userRepository;

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
		http
        .authorizeRequests()
          .antMatchers("/api/auth").permitAll()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .logout().logoutUrl("/api/logout").logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))).permitAll()
          .and().csrf().disable();
	}

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
				.allowedOriginPatterns("*")
        .allowCredentials(true)
				.allowedHeaders("Origin", "Content-Type", "X-Auth-Token")
        .allowedMethods("POST", "GET", "OPTIONS", "DELETE", "PUT");
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
