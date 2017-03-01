package com.jutem.suggestion.model.trie;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 字典树节点
 */
@Document
public class TrieNode {
	
	private ObjectId id;
	private boolean isLeaf;
	private String word = "";
	private int count;
	private ObjectId[] children = new ObjectId[26]; //26个字母
	
	public void incrementCount() {
		this.count ++;
	}

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public ObjectId[] getChildren() {
		return children;
	}
	public void setChildren(ObjectId[] children) {
		this.children = children;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
