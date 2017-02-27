package com.jutem.suggestion.model;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jutem.suggestion.dao.TrieTreeDAO;

/**
 * 字典树
 * 只支持英文
 */
@Component
public class TrieTree {
	
	@Autowired
	private TrieTreeDAO trieTreeDAO;

	private TrieNode root;
	
	@PostConstruct
	public void init() {
		this.root = trieTreeDAO.findRoot();
	}
	
	public void insert(String word) {
		if(word == null)
			throw new RuntimeException("不允许插入空值"); 
		
		List<TrieNode> children = root.getChildren();
		
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = 'a' - c;
			TrieNode node = children.get(index);
			//TODO persist
			if(node == null ) {
				node = new TrieNode();
				node.setC(c);
				children.add(index, node);
			}
			
			children = node.getChildren();
			if(i == word.length() - 1)
				node.setLeaf(true);
		}
	}
	
	public TrieNode searchNode(String word) {
		if(word == null)
			return null;
		List<TrieNode> children = root.getChildren();
		
		TrieNode node = null;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = 'a' - c;
			node = children.get(index);
			if(node == null)
				return null;
			children = node.getChildren();
		}
		return node;
	}

}
