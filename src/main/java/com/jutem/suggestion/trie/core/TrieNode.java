package com.jutem.suggestion.trie.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树节点
 * compareable实现count从大到小排列
 */
public class TrieNode implements Comparable<TrieNode>{
	
	private boolean isLeaf;
	private char c;
	private Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

	private int count;
	private String word;
	
	public void incrementCount() {
		this.count ++;
	}
	
	@Override
	public int compareTo(TrieNode o) {
		if(this.count > o.getCount())
			return -1;
		if(this.count < o.getCount())
			return 1;
		return 0;
	}
	
	/********** get/set ***********/
	
	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(Map<Character, TrieNode> children) {
		this.children = children;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
