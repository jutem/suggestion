package com.jutem.suggestion.service.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutem.suggestion.model.TrieNode;
import com.jutem.suggestion.model.TrieTree;
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
	
	@Override
	public void findTop(String word, int n) {
		TrieNode trieNode = trieTree.searchNode(word);
	}

	@Override
	public void insertRandomCases() {
		for(int i = 0; i < 100; i++) {
			int sLength = ThreadLocalRandom.current().nextInt(3, 10);
			String word = CommonUtil.RandomString(sLength);
			insert(word);
		}
	}
	
}
