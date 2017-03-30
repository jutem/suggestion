package com.jutem.suggestion.config;

import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.handler.InsertWordHandler;
import com.jutem.suggestion.trie.handler.impl.LocalInsertHandler;
import com.jutem.suggestion.trie.persist.TriePersist;
import com.jutem.suggestion.trie.persist.impl.SimpleTriePersist;
import com.jutem.suggestion.trie.search.ChildrenSearch;
import com.jutem.suggestion.trie.search.impl.DefaultChildrenSearch;
import org.springframework.context.annotation.*;
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

	@Bean
	public InsertWordHandler insertWordHandler(TrieTree trieTree, TriePersist triePersist) {
		return new LocalInsertHandler(triePersist, trieTree);
	}
}
