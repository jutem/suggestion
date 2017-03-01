package com.jutem.suggestion.service;

import java.util.List;

public interface SuggestionService {

	/**
	 * 查询拥有word为前缀的，出现此处最多的k个字符串
	 * @return 
	 */
	List<String> findTopK(String word, int K);

	void insert(String word);
	
	/**
	 * 批量生成测试数据的接口
	 */
	void insertRandomCases();

}
