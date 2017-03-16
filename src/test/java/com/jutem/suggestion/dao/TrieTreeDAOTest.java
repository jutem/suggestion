package com.jutem.suggestion.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jutem.suggestion.base.BaseTest;
import com.jutem.suggestion.trie.core.TrieNode;

public class TrieTreeDAOTest extends BaseTest{
	
	@Autowired
	private TrieTreeDAO dao;
	
	@Test
	public void addNode() {
		TrieNode node = new TrieNode();
		dao.addNode(node);
	}
	
	@Test
	public void findRoot() {
		TrieNode root = dao.findRoot();
		System.out.println(root.isLeaf());
	}

	@Test
	public void findById() {
		TrieNode root = dao.findById("ROOT");
		System.out.println(root.isLeaf());
	}
}