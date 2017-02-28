package com.jutem.suggestion.model;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
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
		
		TrieNode parent = root;
		
		for(int i = 0; i < word.length(); i++) {
			ObjectId[] children = parent.getChildren();

			char c = word.charAt(i);
			int index = c - 'a';
			ObjectId childId = children[index];
			
			TrieNode child;
			if(childId == null) {
				//插入子节点
				child = new TrieNode();
				child.setC(c);
				trieTreeDAO.addNode(child);
				//更新父节点
				children[index] = child.getId();
				trieTreeDAO.updateNode(parent.getId(), parent);
			} else {
				child = trieTreeDAO.findById(childId);
			}
			
			if(i == word.length() - 1) {
				child.setLeaf(true);
				trieTreeDAO.updateNode(child.getId(), child);
			}
			//切换引用
			parent = child;
		}
	}
	
	public TrieNode searchNode(String word) {
		if(word == null)
			return null;
		ObjectId[] children = root.getChildren();
		
		TrieNode node = null;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			ObjectId childId = children[index];
			if(childId == null)
				return null;
			node = trieTreeDAO.findById(childId);
			children = node.getChildren();
		}
		return node;
	}

}
