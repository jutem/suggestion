package com.jutem.suggestion.service;

public interface SuggestionService {

	void findTop(String word, int n);

	void insert(String word);
	
	/**
	 * 批量生成测试数据的接口
	 */
	void insertRandomCases();

}
