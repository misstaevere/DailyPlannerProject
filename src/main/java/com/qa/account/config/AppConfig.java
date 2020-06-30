package com.qa.account.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
