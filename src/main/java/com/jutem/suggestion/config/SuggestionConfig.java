package com.jutem.suggestion.config;

import org.springframework.context.annotation.*;

import com.jutem.suggestion.search.ChildrenSearch;
import com.jutem.suggestion.search.impl.DefaultChildrenSearch;
import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.persist.TriePersist;
import com.jutem.suggestion.trie.persist.impl.SimpleTriePersist;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.jutem.suggestion",
	excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@Import({MongoConfig.class})
public class SuggestionConfig {
	
	@Bean
	public TriePersist triePersist() {
		return new SimpleTriePersist();
	}
	
	@Bean
	public ChildrenSearch childrenSearch() {
		return new DefaultChildrenSearch();
	}
	
	@Bean
	public TrieTree trieTree(TriePersist triePersist) {
		return triePersist.loadTree();
	}
	
	
}
