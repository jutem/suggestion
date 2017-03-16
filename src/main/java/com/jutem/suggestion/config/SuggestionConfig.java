package com.jutem.suggestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jutem.suggestion.search.ChildrenSearch;
import com.jutem.suggestion.search.impl.ChildrenSearchImpl;
import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.persist.TriePersist;
import com.jutem.suggestion.trie.persist.impl.SimpleTriePersistImpl;

@Configuration
@ComponentScan("com.jutem.suggestion") 
@Import({MongoConfig.class})
public class SuggestionConfig {
	
	@Bean
	public TriePersist triePersist() {
		return new SimpleTriePersistImpl(); 
	}
	
	@Bean
	public ChildrenSearch childrenSearch() {
		return new ChildrenSearchImpl();
	}
	
	@Bean
	public TrieTree trieTree(TriePersist triePersist) {
		return triePersist.loadTree();
	}
	
	
}
