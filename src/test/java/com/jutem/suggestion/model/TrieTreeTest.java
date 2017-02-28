package com.jutem.suggestion.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jutem.suggestion.base.BaseTest;

public class TrieTreeTest extends BaseTest{
	
	@Autowired
	private TrieTree trieTree;

	@Test
	public void insert() {
		trieTree.insert("testas");
	}
	
	@Test
	public void searchNode() {
		TrieNode node = trieTree.searchNode("test");
		Assert.assertNotNull(node);
	}
	
	
}
