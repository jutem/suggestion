package com.jutem.suggestion.service;

import com.jutem.suggestion.trie.core.TrieNode;

import java.util.List;

public interface SuggestionService {

	/**
	 * 查询拥有word为前缀的，出现此处最多的k个字符串
	 */
	List<String> findTopK(String word, int K);

	/**
	 * 插入字符串
	 */
	void insertWord(String word);
	
	/**
	 * 查询word的节点
	 */
	TrieNode searchNode(String word);

	/**
	 * 保存当前内存中的树
	 */
	void saveTree();

	/**
	 * 批量生成测试数据的接口
	 * 测试接口
	 */
	void insertRandomCases(int num);
}
