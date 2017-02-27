package com.jutem.suggestion.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 字典树节点
 */
@Document
public class TrieNode {
	private boolean isLeaf;
	private char c;
	private List<TrieNode> children = new ArrayList<TrieNode>(24);

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
	public List<TrieNode> getChildren() {
		return children;
	}
	public void setChildren(List<TrieNode> children) {
		this.children = children;
	}
}
