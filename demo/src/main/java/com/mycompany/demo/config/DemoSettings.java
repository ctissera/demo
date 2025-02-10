package com.mycompany.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.mycompany.demo.maxvalues")
public class DemoSettings {
	
	public static final Integer MAX_MOVIES_PERSON = 1;


}
