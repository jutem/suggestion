package com.jutem.suggestion.trie.persist;

import com.jutem.suggestion.trie.core.TrieNode;
import com.jutem.suggestion.trie.core.TrieTree;

/**
 * trie持久化接口
 */
public interface TriePersist {
	/**
	 * 加载树 
	 */
	TrieTree loadTree();
	/**
	 * 保存树
	 */
	void saveTree(TrieTree trieTree);
	/**
	 * 增加节点 
	 */
	TrieNode addNode();
	/**
	 * 更新节点
	 */
	boolean updateNode();
}
