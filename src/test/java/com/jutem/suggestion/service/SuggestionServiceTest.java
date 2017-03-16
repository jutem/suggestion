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
		suggestionService.insert("hello");
		suggestionService.insert("hello");
		suggestionService.insert("hello");
		suggestionService.insert("hello");
		suggestionService.insert("hello");

		suggestionService.insert("helloa");
		suggestionService.insert("hellob");
		suggestionService.insert("helloc");
		suggestionService.insert("hellod");

		suggestionService.saveTree();
	}
	
	@Test
	public void findTopKTest() {
		List<String> result = suggestionService.findTopK("h", 10);
		System.out.println("<<<< result : " + JSON.toJSONString(result));
	}
	
	@Test
	public void searchNode() {
		TrieNode result = suggestionService.searchNode("hyn");
		System.out.println(JSON.toJSON(result));
	}
}
