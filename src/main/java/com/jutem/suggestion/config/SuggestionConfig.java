package com.jutem.suggestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jutem.suggestion.model.TrieNode;

@Configuration
@ComponentScan("com.jutem.suggestion") 
@Import({MongoConfig.class})
public class SuggestionConfig {
	
	@Bean
	public TrieNode root() {
		return new TrieNode();
	}
	
}
