package com.scnsoft.cocktails;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.scnsoft.cocktails.rest.Subscription;

@SpringBootApplication
@EnableCaching
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CocktailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailsApplication.class, args);
	}
	
	@Bean
	public Map<String, Subscription> emptyMap() {
		return Collections.emptyMap();
	}
}
