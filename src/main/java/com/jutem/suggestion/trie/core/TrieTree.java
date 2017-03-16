package com.jutem.suggestion.trie.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.jutem.suggestion.exceptionn.SuggestionException;

/**
 * 字典树
 */
@Document
public class TrieTree {
	
	private String id;
	private TrieNode root;
	
	public TrieTree(TrieNode root) {
		if(root == null)
			throw new SuggestionException("empty root");
		this.root = root;
	}
	
	public void insert(String word) {
		if(word == null)
			throw new RuntimeException("不允许插入空值"); 
		
		Map<Character, TrieNode> children = root.getChildren();
		
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			TrieNode node;
			if(children.containsKey(c)) {
				node = children.get(c);
			} else {
				node = new TrieNode();
				node.setC(c);
				node.setChildren(new HashMap<Character, TrieNode>());
				children.put(c, node);
			}

			children = node.getChildren();
			if(i == word.length() - 1) {
				node.incrementCount();
				node.setWord(word);
				node.setLeaf(true);
			}

		}
	}
	
	public TrieNode searchNode(String word) {
		if(word == null)
			return null;
		Map<Character, TrieNode> children = root.getChildren();
		
		TrieNode node = null;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(children.containsKey(c)) {
				node = children.get(c);
				children = node.getChildren();
			} else {
				return null;
			}
		}
		return node;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TrieNode getRoot() {
		return root;
	}

	public void setRoot(TrieNode root) {
		this.root = root;
	}
	
}
