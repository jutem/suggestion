package com.jutem.suggestion.search;

import java.util.List;

import com.jutem.suggestion.trie.core.TrieNode;

public interface ChildrenSearch {
	/**
	 * 返回传入node的k个node
	 * @param node 需要遍历的节点
	 * @param k 返回count最大的k个元素
	 * @throws InterruptedException 
	 */
	List<TrieNode> searchChidren(TrieNode node, int k);
}
