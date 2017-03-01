package com.jutem.suggestion.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.jutem.suggestion.model.trie.TrieNode;

@Repository
public class TrieTreeDAO {
	
	private static final ObjectId ROOT_ID = new ObjectId("58b6b04d58f2b16ec9adfa59");
	private static final String COL = "suggestion_tree";
	
	@Autowired
	private MongoOperations mongo;
	
	public TrieNode findRoot() {
		return mongo.findById(ROOT_ID, TrieNode.class, COL);
	}
	
	public void addNode(TrieNode node) {
		mongo.insert(node, COL);
	}

	public void updateNode(ObjectId id, TrieNode node) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));

		Update update = new Update();
		update.set("children", node.getChildren());
		update.set("isLeaf", node.isLeaf());
		update.set("count", node.getCount());
		
		mongo.updateFirst(query, update, COL);
	}

	public TrieNode findById(Object id) {
		return mongo.findById(id, TrieNode.class, COL);
	}
	
	
}
