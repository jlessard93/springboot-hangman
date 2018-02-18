package com.nexmo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

import com.nexmo.dto.HangmanSessionBean;
import com.nexmo.filters.GuessLetterFilter;


//implement WebApplicationInitializer to configure web.xml programmatically
@SpringBootApplication(scanBasePackages = {"com.nexmo.hangman",
		"com.nexmo.controllers",
		"com.nexmo.sessionlisteners",
		"com.nexmo.services",
		"com.nexmo.dao"})
@EntityScan("com.nexmo.entities")
public class HangmanApplication implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

	}
	
	@Bean
	public HangmanSessionBean hangmanSessionBean() {
		return new HangmanSessionBean();
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(guessLetterFilter());
	    registration.addUrlPatterns("/api/v1/hangman/guessword");
	    registration.setName("guessLetterFilter");
	    return registration;
	}
	
	@Bean
	public GuessLetterFilter guessLetterFilter() {
		return new GuessLetterFilter();
	}
	
	//MANAGEMENT PAGE IS ONLY ACCESSIBLE BY THE MACHINE IN WHICH THE JAR IS DEPLOYED/RAN
//	@Bean
//	public FilterRegistrationBean remoteAddressFilter() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//		RemoteAddrFilter filter = new RemoteAddrFilter();
//		filter.setAllow("127.0.1.1");
//		filterRegistrationBean.setFilter(filter);
//		filterRegistrationBean.addUrlPatterns("/hangman/management");
//		return filterRegistrationBean;
//	}
	
	
	
}





