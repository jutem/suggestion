package com.jutem.suggestion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.jutem.suggestion.model.TrieNode;

@Repository
public class TrieTreeDAO {
	
	private static final String ROOT_ID = "root";
	private static final String COL = "suggestion_tree";
	
	@Autowired
	private MongoOperations mongo;
	
	public TrieNode findRoot() {
		return mongo.findById(ROOT_ID, TrieNode.class);
	}
	
	public void addNode(TrieNode node) {
		mongo.insert(node, COL);
	}

	public TrieNode findById(String id) {
		return mongo.findById(id, TrieNode.class);
	}
	
}
