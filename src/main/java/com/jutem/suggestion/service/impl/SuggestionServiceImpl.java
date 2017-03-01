package com.jutem.suggestion.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutem.suggestion.model.trie.TrieNode;
import com.jutem.suggestion.model.trie.TrieTree;
import com.jutem.suggestion.service.SuggestionService;
import com.jutem.suggestion.util.CommonUtil;

@Service
public class SuggestionServiceImpl implements SuggestionService{
	
	@Autowired
	private TrieTree trieTree;
	
	@Override
	public void insert(String word) {
		trieTree.insert(word);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTopK(String word, int K) {
		TrieNode trieNode = trieTree.searchNode(word);
		if(trieNode == null)
			return Collections.EMPTY_LIST;
		
		//
		trieNode.getChildren();
		
		return null;
	}

	@Override
	public void insertRandomCases() {
		for(int i = 0; i < 100000; i++) {
			int sLength = ThreadLocalRandom.current().nextInt(3, 10);
			String word = CommonUtil.RandomString(sLength);
			insert(word);
		}
	}
	
}
