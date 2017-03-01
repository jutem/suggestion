package com.jutem.suggestion.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 字典树节点
 */
@Document
public class TrieNode {
	
	private ObjectId id;
	private boolean isLeaf;
	private char c;
	private int count;
	private ObjectId[] children = new ObjectId[26];

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
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
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
