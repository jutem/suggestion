package com.jutem.suggestion.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.jutem.suggestion") 
@Import({MongoConfig.class})
public class SuggestionConfig {
	
}
