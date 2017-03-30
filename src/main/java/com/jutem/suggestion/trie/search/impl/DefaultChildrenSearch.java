package com.jutem.suggestion.trie.search.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.jutem.suggestion.exceptionn.SuggestionException;
import com.jutem.suggestion.trie.search.ChildrenSearch;
import com.jutem.suggestion.trie.core.TrieNode;

public class DefaultChildrenSearch implements ChildrenSearch {
	
	private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
	//每个子节点超时时间
	private static final int TIME_OUT = 10;
	//查找最长的深度
	private static final int MAX_DEEP = 10;

	@Override
	public List<TrieNode> searchChidren(TrieNode node, int k) {
		if(node == null || k < 1) 
			throw new SuggestionException("params exception");

		Map<Character, TrieNode> children = node.getChildren();
		List<SearchWork> works = new ArrayList<SearchWork>();
		for(Map.Entry<Character, TrieNode> entry : children.entrySet()) {
			TrieNode childNode = entry.getValue();
			SearchWork work = new SearchWork(childNode, MAX_DEEP);
			works.add(work);
		}
		
		List<TrieNode> result = new LinkedList<TrieNode>();
		List<Future<List<TrieNode>>> futures;
		try {
			//timeout为所有任务执行完成的总超时时间
			long start = System.currentTimeMillis();
			futures = pool.invokeAll(works, TIME_OUT, TimeUnit.MILLISECONDS);
			long end = System.currentTimeMillis();
			System.out.println("<<<< end : " + (end - start));
		} catch(Exception e) {
			throw new SuggestionException(e.getCause());
		}
		Iterator<SearchWork> workIter = works.iterator();
		for(Future<List<TrieNode>> f : futures) {
			SearchWork work = workIter.next();
			List<TrieNode> sortList;
			try {
				sortList = f.get();
			} catch (Exception e) { 
				e.printStackTrace();
				while(true) {
					if(f.isCancelled()) {
						sortList = work.getResult();
						Collections.sort(sortList);
						break;
					}
				}
			}
			
			if(sortList.size() > k)
				sortList = sortList.subList(0, k);
			result.addAll(sortList);
		}
		
		Collections.sort(result);
		if(result.size() > k)
			result = result.subList(0, k);
		return result;
	}
	
	
	private class SearchWork implements Callable<List<TrieNode>> {
		
		private static final int startDeepNo = 1;

		//原始查询节点
		private TrieNode node;
		//结果集
		private List<TrieNode> result = new LinkedList<TrieNode>();
		//最大查询深度
		private int maxDeep;

		public SearchWork(TrieNode node, int maxDeep) {
			super();
			this.node = node;
			this.maxDeep = maxDeep;
		}
		
		@Override
		public List<TrieNode> call() throws Exception {
			deepSearch(node, startDeepNo);
			Collections.sort(result);
			return result;
		}
		
		private void deepSearch(TrieNode parent, int deepNo) {
			if(deepNo > maxDeep)
				return;
			if(parent.isLeaf())
				result.add(parent);
			
			Map<Character, TrieNode> children = parent.getChildren();
			for(Map.Entry<Character, TrieNode> entry : children.entrySet()) {
				TrieNode node = entry.getValue();
				deepSearch(node, deepNo + 1);
			}
		}

		public List<TrieNode> getResult() {
			return result;
		}
		
	}
}
