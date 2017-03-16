package com.jutem.suggestion.trie.persist.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.jutem.suggestion.exceptionn.SuggestionException;
import com.jutem.suggestion.trie.core.TrieNode;
import com.jutem.suggestion.trie.core.TrieTree;
import com.jutem.suggestion.trie.persist.TriePersist;

public class TriePersistImpl implements TriePersist {
	
	private static final ObjectId ROOT_ID = new ObjectId("58b6b04d58f2b16ec9adfa59");
	private static final String COL = "suggestion_tree";
	
	@Autowired
	private MongoOperations mongo;
	
	@Override
	public TrieTree loadTree() {
		
		TrieNodeDO rootDO = findRoot();
		TrieNode root = transferDO(rootDO);
		//加载节点
		Map<Character, ObjectId> childrenDO = rootDO.children;
		
		TrieTree tree = new TrieTree(root);
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

	/**
	 * 查询root节点
	 */
	private TrieNodeDO findRoot() {
		TrieNodeDO root = mongo.findById(ROOT_ID, TrieNodeDO.class, COL);
		if(root == null) {
			root = new TrieNodeDO(false, '\0', 0, new HashMap<Character, ObjectId>());
			addNode(root);
		}
		return root;
	}
	
	/**
	 * 填充节点
	 */
	private void fillNode(TrieNode node, TrieNodeDO nodeDO) {
		if(node == null || nodeDO == null)
			throw new SuggestionException("root or rootDO is null");
		Map<Character, ObjectId> childrenDO = nodeDO.children;
		if(childrenDO == null || childrenDO.size() == 0)
			return;

		Map<Character, TrieNode> children = node.getChildren();
		
		Collection<ObjectId> objectIds = childrenDO.values();
		List<TrieNodeDO> childrenNodeDOs = findByIds(objectIds);
		for(TrieNodeDO childrenNodeDO : childrenNodeDOs) {
			TrieNode trieNode = transferDO(childrenNodeDO);
			children.put(childrenNodeDO.c, trieNode);
		}
	}
	
	/**
	 * 新增节点
	 */
	private void addNode(TrieNodeDO node) {
		mongo.insert(node, COL);
	}
	
	/**
	 * 批量根据ids获取
	 */
	private List<TrieNodeDO> findByIds(Collection<ObjectId> ids) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").in(ids));
		return mongo.find(query, TrieNodeDO.class);
	}
	
	private TrieNode transferDO(TrieNodeDO trieNodeDO) {
		if(trieNodeDO == null) 
			throw new SuggestionException("empty node");
		TrieNode node = new TrieNode();
		node.setC(trieNodeDO.c);
		node.setLeaf(trieNodeDO.isLeaf);
		node.setCount(trieNodeDO.count);
		return node;
	}
	
	/**
	 * 持久化叶子节点对象
	 */
	private class TrieNodeDO {
		char c;
		boolean isLeaf;
		int count;
		Map<Character, ObjectId> children;

		public TrieNodeDO(boolean isLeaf, char c, int count, Map<Character, ObjectId> children) {
			this.isLeaf = isLeaf;
			this.count = count;
			this.c = c;
			this.children = children;
		}
	}

	@Override
	public void saveTree(TrieTree tree) {
	}
	
	
	/*********** others *******/
	
	
//	public TrieNode findById(Object id) {
//		return mongo.findById(id, TrieNode.class, COL);
//	}
//	
//	public List<TrieNode> findByIds(List<ObjectId> ids) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("_id").in(ids));
//		return mongo.find(query, TrieNode.class);
//	}
//	
//	public void updateNode(ObjectId id, TrieNode node) {
//		
//		Query query = new Query();
//		query.addCriteria(Criteria.where("_id").is(id));
//
//		Update update = new Update();
//		update.set("children", node.getChildren());
//		update.set("isLeaf", node.isLeaf());
//		update.set("count", node.getCount());
//		
//		mongo.updateFirst(query, update, COL);
//	}

}
