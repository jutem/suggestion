package com.jutem.suggestion.trie.persist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.jutem.suggestion.exceptionn.SuggestionException;
import com.jutem.suggestion.trie.core.TrieNode;
import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.persist.TriePersist;

public class SimpleTriePersist implements TriePersist {
	
	private static final String TREE_ID = "NO_1";
	private static final String COL = "TrieTree";
	
	@Autowired
	private MongoOperations mongo;
	
	@Override
	public TrieTree loadTree() {
		TrieTree tree = mongo.findById(TREE_ID, TrieTree.class, COL);
		if(tree == null) {
			tree = new TrieTree(new TrieNode());
			saveTree(tree);
		}
		return tree;
	}

	@Override
	public TrieNode addNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveTree(TrieTree tree) {
		if(tree == null)
			throw new SuggestionException("tree is empty");
		tree.setId(TREE_ID);
		mongo.save(tree, COL);
	}

}
