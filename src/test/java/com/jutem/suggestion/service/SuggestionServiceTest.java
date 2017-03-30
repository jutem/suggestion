package com.jutem.suggestion.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jutem.suggestion.base.BaseTest;
import com.jutem.suggestion.trie.core.TrieNode;

public class SuggestionServiceTest extends BaseTest{
	
	@Autowired
	public SuggestionService suggestionService;
	
	@Test
	public void insertCases() {
		suggestionService.insertRandomCases(10000);
	}
	
	@Test
	public void insertTest() {
		suggestionService.insertWord("helloabcdefg");
		suggestionService.insertWord("helloabcdefg");
		suggestionService.insertWord("helloabcdefg");
		suggestionService.insertWord("helloabcdefg");
		suggestionService.insertWord("helloabcdefg");

		suggestionService.insertWord("helloa");
		suggestionService.insertWord("hellob");
		suggestionService.insertWord("helloc");
		suggestionService.insertWord("hellod");

		suggestionService.saveTree();
	}
	
	@Test
	public void findTopKTest() {
		List<String> result = suggestionService.findTopK("he", 100);
		System.out.println("<<<< result : " + JSON.toJSONString(result));
	}
	
	@Test
	public void searchNode() {
		TrieNode result = suggestionService.searchNode("helloa");
		System.out.println(JSON.toJSON(result));
	}
}
