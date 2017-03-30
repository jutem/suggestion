package com.jutem.suggestion.service.impl;

import com.jutem.suggestion.service.SuggestionService;
import com.jutem.suggestion.trie.core.TrieNode;
import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.handler.InsertWordHandler;
import com.jutem.suggestion.trie.persist.TriePersist;
import com.jutem.suggestion.trie.search.ChildrenSearch;
import com.jutem.suggestion.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SuggestionServiceImpl implements SuggestionService{
	
	@Autowired
	private TrieTree trieTree;
	
	@Autowired
	private TriePersist triePersist;

	@Autowired
	private InsertWordHandler insertWordHandler;
	
	@Autowired
	private ChildrenSearch childrenSearch;

	@Override
	public void insertWord(String word) {
		insertWordHandler.insertWord(word);
	}
	
	@Override
	public void saveTree() {
		triePersist.saveTree(trieTree);
	}
	
	
	@Override
	public TrieNode searchNode(String word) {
		return trieTree.searchNode(word);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTopK(String word, int k) {
		TrieNode trieNode = trieTree.searchNode(word);
		if(trieNode == null)
			return Collections.EMPTY_LIST;

		List<TrieNode> topKTrieNodes = childrenSearch.searchChidren(trieNode, k);
		
		List<String> result = new ArrayList<String>(topKTrieNodes.size());
		for(TrieNode node : topKTrieNodes)
			result.add(node.getWord());
		return result;
	}

	@Override
	public void insertRandomCases(int num) {
		for(int i = 0; i < num; i++) {
			int sLength = ThreadLocalRandom.current().nextInt(3, 8);
			String word = CommonUtil.RandomString(sLength);
			insertWord(word);
		}
		triePersist.saveTree(trieTree);
	}
	
}
