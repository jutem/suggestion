package com.jutem.suggestion.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jutem.suggestion.base.BaseTest;
import com.jutem.suggestion.model.trie.TrieNode;
import com.jutem.suggestion.model.trie.TrieTree;

public class TrieTreeTest extends BaseTest{
	
	@Autowired
	private TrieTree trieTree;

	@Test
	public void insert() {
		trieTree.insert("test");
	}
	
	@Test
	public void searchNode() {
		TrieNode node = trieTree.searchNode("ab");
		Assert.assertNotNull(node);
	}
	
	
}
